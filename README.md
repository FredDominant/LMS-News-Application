
[![CircleCI](https://circleci.com/gh/Noblemajesty/LMS-News-Application/tree/develop.svg?style=svg)](https://circleci.com/gh/Noblemajesty/LMS-News-Application/tree/develop)
# News Application
This is an Android application that consumes New York Times [Top Stories API](https://any-api.com/nytimes_com/top_stories/docs/API_Description) and displays the following news headlines and abstracts:
1. Top News
2. Sports News
3. Food News

# Features
1. Currently, users can view News headlines and abstract provided by the New York Times [Top Stories API](https://any-api.com/nytimes_com/top_stories/docs/API_Description)
2. Users can also navigate to different news types by clicking on the bottom Navigation links.
3. Users can also view news details offline.

# Limitations
The API doesn't provide the news details, so users currently only view Headlines and Abstracts, and `NOT` news articles.

## Tools Used
1. Kotlin
2. RxJava/Kotlin
3. [Retrofit](https://square.github.io/retrofit/)
4. SQLite Database
5. [Picasso](http://square.github.io/picasso/)
6. Junit, Espresso and Mockito

# Test
1. To run Instrumented/UI tests, `./gradlew connectedAndroidTest`
2. To run Unit Tests, `./gradlew test`

# Download
The link to the already built version of the app can be found [here](http://www.droidbin.com/p1d1vpne1t1qjp1urg6v41tv9vcf3)

# How to install and run
1. Clone this repo by running `git@github.com:Noblemajesty/LMS-News-Application.git`
2. Get an API key from [here](https://developer.nytimes.com/get-started)
3. In the cloned repo, navigate to the `app/src/main/java/com/noblemajesty/newsapplication/utils` folder.
4. In the `utils` folder, create a new class/file `Secret.kt`
5. Ensure the newly created `Secret.kt` file is empty.
6. Copy the following code into the file
    ```
    package com.noblemajesty.newsapplication.utils

    class Secret {
        companion object { const val apiKey = "Your API Key from New York Times" }
    }
    ```
7. Build the app.

Contributions are welcome!