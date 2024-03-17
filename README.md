<h1 align="center">
 MovieApp
</h1>
<p align="center">
 Android Studio, Kotlin
</p>

<p align="center">
 <img src="https://github.com/catalyn98/MovieApp-application/blob/main/AndroidStudio.png" />
</p>

# Tech stack
MovieApp uses a number of open source projects to work properly:
* [Android Studio](https://en.wikipedia.org/wiki/Android_Studio) - is the official integrated development environment for Google's Android operating system, built on JetBrains' IntelliJ IDEA software and designed specifically for Android development.
* [Kotlin](https://en.wikipedia.org/wiki/Kotlin_(programming_language)) -  is a cross-platform, statically typed, general-purpose programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference allows its syntax to be more concise.

# The data model and its description
The data provided by this application is obtained through the API offered by [THEMOVIEDB.ORG](https://www.themoviedb.org). The data required to display movie details is returned to the application in the JSON format.

# Technical specifications
* IDE used: Android Studio, version 2020.3.
* Used APIs from [THEMOVIEDB.ORG](https://www.themoviedb.org) to obtain movie information in JSON format through an API access key.
* [Room](https://developer.android.com/training/data-storage/room) was used to implement a local database for saving favorite movies. By room repository, data access is done locally through SQLite and by retrofit, data access is done from a server using networking.
* The Moshi library was used for easier conversion from JSON format to Java objects.
* The Glide library was used for image loading.
* Data Access Objects (DAO) was used for interacting with the database and handling storage. 
* Data Transfer Objects (DTO) was used for data retrieval, serialization, deserialization, and transfer.

# Web application screenshots 
| <p align="center">**Splash screen**</p> | <p align="center">**Prefernces screen**</p> | <p align="center">**Choose actors screen**</p> |
| ------------ | ------------ | ------------ |
| <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/1.Splashscreen.jpg" />  |  <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/2.PreferencesScreen.jpg" /> | <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/3.ChooseActorsScreen.jpg" /> |
| <p align="center">**Choose genres screen**</p> | <p align="center">**List of movies screen**</p> | <p align="center">**Details movies screen**</p> |
| <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/4.ChooseGenresScreen.jpg" />| <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/5.ListOfMoviesScreen.jpg" /> | <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/6.DetailsMoviesScreen.jpg" /> |
| <p align="center">**Favourites movies screen**</p> | <p align="center">**Watched movies screen**</p> | <p align="center">**Application menu**</p> |
| <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/7.FavoritesMoviesScreen.jpg" /> | <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/8.WatchedMoviesScreen.jpg" /> | <img src="https://github.com/catalyn98/MovieApp-application/blob/main/Screenshots/9.ApplicationMenu.jpg" /> |
