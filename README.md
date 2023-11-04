# HarryPotterCharactersApp

Android Clean Architecture with Kotlin, MVVM, Jetpack Compose, Hilt, MockK, Coroutines, and Kotlin Flow
This is a template for an Android project that demonstrates how to build an application using clean architecture principles along with modern Android development technologies. It's designed to showcase best practices and provide a starting point for your own projects.

## Table of Contents
* Introduction
* Features
* Tech Stack
* Architecture

### Introduction
This Android app is designed to display a list of characters from the Harry Potter series. Users can click on any character to view more details about them, and there is also a search feature to help users find specific characters.

### Features
List the key features of your application. This could include anything from basic functionalities to advanced capabilities.

* Display a list of Harry Potter series characters.
* View detailed information about each character by clicking on their profile.
* Search for specific characters by name or other attributes.
* User-friendly and intuitive interface.
...
### Tech Stack
The technologies and libraries used in this project.

* Kotlin
* Clean Architecture
* MVVM (Model-View-ViewModel) pattern
* Jetpack Compose for UI
* Hilt for dependency injection
* MockK for unit testing
* Coroutines for asynchronous programming
* Kotlin Flow for reactive programming

### Architecture
Clean Architecture encourages the separation of concerns and a clear division of responsibilities, which ultimately leads to more maintainable and scalable Android applications.

#### Presentation Layer (UI):
This layer is responsible for handling the user interface and user interactions.
It includes activities, fragments, views, and other UI components.
The presentation layer should be as minimal as possible and focused on rendering UI elements and handling user input. It should delegate business logic to the layers below.

#### Domain Layer (Use Cases):
The domain layer contains the core business logic of the application.
It defines use cases, which are high-level operations that the application can perform.
Use cases are independent of the user interface or external systems and encapsulate the business rules of the application.

#### Data Layer (Repositories and Data Sources):
The data layer is responsible for managing data and interacting with external systems such as databases, APIs, and local storage.
It includes repositories that provide a clean and abstract interface for data access.
Data sources (e.g., remote data source, local data source) implement the concrete logic to fetch and store data.

#### Dependency Injection (DI):
Dependency Injection is a crucial part of Clean Architecture. It ensures that dependencies between layers are properly managed.
DI frameworks like Dagger or Hilt are commonly used to provide the necessary dependencies to each layer.
