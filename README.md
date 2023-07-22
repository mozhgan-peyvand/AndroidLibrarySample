# AndroidLibrarySample

AndroidLibrarySample is a project that demonstrates how to create and publish Android libraries locally using Maven and how to use them in other projects. The project utilizes Retrofit and Jetpack Compose to fetch data from an API and display it in a sample app.

## Project Structure

The project consists of four modules:

1. **dependent-library-one**: This is the first library module that contains reusable code and functionality. It will be published locally to your Maven repository.

2. **dependent-library-two**: This module depends on `dependent-library-one` and uses its functionality. It will also be published locally to your Maven repository.

3. **main-library-published**: This module is the main library that combines `dependent-library-one` and `dependent-library-two`. It will be published locally as a standalone library.

4. **app**: This is the sample app module that demonstrates how to use the `main-library-published` in an Android application.

## Getting Started

To get started with this project, follow these steps:

### 1. Clone the repository

First, clone this repository to your local machine using the following command:
```groovy
git clone https://github.com/mozhgan-peyvand/AndroidLibrarySample.git
```
### 2. Publish `dependent-library-one` to Maven Local

Before publishing `dependent-library-two` and `main-library-published`, we need to publish `dependent-library-one` to your local Maven repository. To do this, navigate to the `dependent-library-one` directory and run the following command:
```groovy
./gradlew dependent-library-one:build
```
```groovy
./gradlew dependent-library-one:publishToMavenLocal
```
This will build the library and publish it to your local Maven repository (`~/.m2/repository`).

### 3. Publish `dependent-library-two` to Maven Local

Next, navigate to the `dependent-library-two` directory and run the following command:
```groovy
./gradlew dependent-library-two:build
```
```groovy
./gradlew dependent-library-two:publishToMavenLocal
```
This will build the library and publish it to your local Maven repository.

### 4. Publish `main-library-published`

Now, navigate to the `main-library-published` directory and run the following command:
```groovy
./gradlew main-library-published:build
```
```groovy
./gradlew main-library-published:publishToMavenLocal
```
This will build the main library and publish it to your local Maven repository.
‍‍
### 5. Use the Libraries in `app`

Finally, to use the published libraries in the `app` module, open the `build.gradle` file of the `app` module and add the following dependency:

```gradle
implementation 'com.example:main-library-published:1.0.0'
```
