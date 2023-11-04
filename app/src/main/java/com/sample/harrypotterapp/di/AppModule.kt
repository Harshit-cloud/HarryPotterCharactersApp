package com.sample.harrypotterapp.di

import com.sample.harrypotterapp.BuildConfig
import com.sample.harrypotterapp.data.remote.CharactersApi
import com.sample.harrypotterapp.data.repository.CharactersRepositoryImpl
import com.sample.harrypotterapp.domain.repository.CharactersRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): CharactersApi =
        retrofit.create(CharactersApi::class.java)

    @Provides
    @Singleton
    fun provideCharacterRepository(api: CharactersApi): CharactersRepository = CharactersRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesIODispatcher() = Dispatchers.IO
}
