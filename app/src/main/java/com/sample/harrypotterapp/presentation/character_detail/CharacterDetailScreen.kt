package com.sample.harrypotterapp.presentation.character_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sample.harrypotterapp.R
import com.sample.harrypotterapp.domain.model.CharacterModel
import com.sample.harrypotterapp.presentation.character_detail.components.TableCell
import com.sample.harrypotterapp.presentation.ui.theme.BackgroundDarkColor

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel,
    navController: NavController,
) {
    val character = viewModel.selectedCharacter
    Box(modifier = Modifier.fillMaxSize().background(BackgroundDarkColor)) {
        Row(modifier = Modifier.padding(top = 6.dp)) {
            Image(
                modifier = Modifier
                    .size(34.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp)
                    .clickable(onClick = {
                        navController.popBackStack()
                    }),
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Character Details",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
                maxLines = 1
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(50.dp))
            val image: Painter =
                rememberAsyncImagePainter(model = character?.image)
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .border(1.dp, Color.White, RoundedCornerShape(100.dp))
                    .align(Alignment.CenterHorizontally),
                painter = image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            character?.name?.let {
                Text(
                    text = it,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TableScreen(character!!)
        }
    }

}

@Composable
fun TableScreen(characterModel: CharacterModel) {

    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .height(600.dp)
    ) {
        // Here is the header
        Row(Modifier.background(Color.Gray)) {
            TableCell(text = "Character", weight = column1Weight)
            TableCell(text = "Details", weight = column2Weight)
        }
        // Here are all the lines of your table.
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "name", weight = column1Weight)
            TableCell(text = characterModel.name, weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "species", weight = column1Weight)
            characterModel.species?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "gender", weight = column1Weight)
            TableCell(text = characterModel.gender, weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "house", weight = column1Weight)
            characterModel.house?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "dateOfBirth", weight = column1Weight)
            characterModel.dateOfBirth?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "yearOfBirth", weight = column1Weight)
            TableCell(text = characterModel.yearOfBirth.toString(), weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "wizard", weight = column1Weight)
            TableCell(text = characterModel.wizard.toString(), weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "ancestry", weight = column1Weight)
            TableCell(text = characterModel.ancestry, weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "eye colour", weight = column1Weight)
            characterModel.eyeColour?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "hair colour", weight = column1Weight)
            characterModel.hairColour?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "patronus", weight = column1Weight)
            characterModel.patronus?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "hogwarts student", weight = column1Weight)
            TableCell(text = characterModel.hogwartsStudent.toString(), weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "hogwarts staff", weight = column1Weight)
            TableCell(text = characterModel.hogwartsStaff.toString(), weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "actor", weight = column1Weight)
            TableCell(text = characterModel.actor, weight = column2Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "alive", weight = column1Weight)
            TableCell(text = characterModel.alive.toString(), weight = column2Weight)
        }
    }
}