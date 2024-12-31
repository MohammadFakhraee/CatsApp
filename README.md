Simple app showing cats and their breeds (and ability to favorite them), created based on modularization, Clean Arch. and MVI architectural pattern for UI.

# Modules
- **Core**: Has _CoroutineContextProvider_ used across other modules
- **Data**: Contains sub-modules of data layer (_Local, Remote, Repository_)
- **Domain**: Contains sub-modules of domain layer (_Models, Use Cases_)
- **Feature**: Contains sub-modules of ui layer (_Core UI, Cats List, Breed Details_)

## Data Layer
- **Local**: Contains implementation of local database using _Room_, required data sources for repositories and all the tests validating the database and data sources
- **Remote**: Contains implementation of remote APIs using _Retrofit_ and _OkHttp_, required data sources for repositories and all the tests validating the remote data sources
- **Repository**: Contains implementation of Cats and Breed repositories to call remote APIs and store/update them while holding previously changed attributes (like favorited cats). It has implemented
all the required test to validate repository logics

## Domain Layer
- **Models**: Contains all the required models of the app while having the base result type (**_Resource_**) and error models
- **Use Case**: Contains all the use cases used by features inside the app mapping the data from data entities to domain entities

## UI Layer
**Core UI**: Contains the application's theme and base structure for MVI architectural pattern (_BaseContract, BaseViewModel_)
**Cats List**: Contains the implementation of CatsList view model, its screen and a route function for external access
**Breed List**: Contains the implementation of BreedDetails view model, its screen and a route function for external access

# Dependencies
There are some external libraries and kotlin concepts used in this app to facilitate the development and tests:
- **Coroutines**: For concurrency
- **Flows**: For concurrent data streams
- **Room**: For database implementation
- **Retrofit**: For API calls
- **Hilt**L For DI
- **Coil**: For loading images from web
- **Compose Navigation**: To implement user navigations inside the app
- **Google Truth**: To ease the test assertions
