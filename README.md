# Fuze Code Challenge

![listing screen](https://user-images.githubusercontent.com/5789073/236656762-68a9e5f3-2691-45d5-8639-092a395e4ca5.png) ![detail screen](https://user-images.githubusercontent.com/5789073/236656800-9bc878df-0829-4659-966c-ac2bbb8aba9b.png)
## General Description

**Architecture**: I used the MVVM (Model-View-ViewModel) architecture. I used interactors as an example to include some business logic rules. As mentioned in "Not Implemented Section", I couldnt' implement local storage (with SQLite or Room solutions).

**Repository**: In the Model layer, I abstracted a repository that provides the necessary data to the ViewModel without the other parts knowing how this data was obtained. The `NetworkBoundResource` class is responsible for the algorithm to obtain the data (allows obtaining data from remote and/or local storage).

**Fetching data mechanisms**: In some cases, I decided to fetch data from the repository and apply some rules in interactors (like adding the data about teams and players into the current "match" being used for detailing). 

![1_-yY0l4XD3kLcZz0rO1sfRA](https://user-images.githubusercontent.com/5789073/236658341-ea2ce7aa-3043-4510-98b9-2907ce03c500.png)

## Technical Description

I used the following libraries and solutions:

- **JetPack Compose**: Library to implement views in Android with a better State paradigm approach. JetPack Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs. [Reference: https://developer.android.com/jetpack/compose]

- **Retrofit**: Used to fetch data remotely. Retrofit is a type-safe HTTP client for Android and Java. It allows you to easily make RESTful requests and parse the response data. [Reference: https://square.github.io/retrofit/]

- **Coroutines**: Used for asynchronous tasks during app execution. Coroutines are a way of managing concurrency in a more simple and efficient way. They allow you to write asynchronous code that looks like synchronous code, without blocking threads. [Reference: https://developer.android.com/kotlin/coroutines?hl=en]

- **Android Architecture Components**: Used for implementing the MVVM architecture. Used LiveData, ViewModel, among others. Android Architecture Components are a collection of libraries that help you design robust, testable, and maintainable apps. They provide common patterns and best practices for app architecture, data persistence, lifecycle management, and more. [Reference: https://developer.android.com/topic/libraries/architecture]

- **Flow**: Used for emiting asynchrounous data to live data. Flow is a type of cold stream that can emit multiple values asynchronously, without blocking the main thread and can be cancelled. Flow is built on top of coroutines and supports reactive patterns. [Reference: https://developer.android.com/kotlin/flow]
## Optionals items implemented

- **MVVM Architecture**
 
- **Reactive Programming (Coroutines with Flow and Livedata)**

- **Responsiveness**

## What was not implemented

- **Mechanism to load all posts from API**: This mechanism occurs, but we do not have control over when to force that. The implemented mechanism fetches from the API if there is no local data, for the sake of simplicity.
- **Unit Tests**: I know how to make Unit Tests, but didn't have time
- **Pagination Support**
- **Offline Storage**: I first started doing abstraction to include local storage with Room, I would take more time due to relational data complexity.
- Pixel perfect views: due to the recent learning of JetPack Compose, it took me more time to learn and implement this new resource so it lacked in some fidelity in design. I'm sorry about that.

## How to run

The app can be built like any other app. Just important to remember to use Java 17 to build the app.
The same applies to tests.
`
