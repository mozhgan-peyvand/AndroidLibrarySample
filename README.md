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

### 4. Publish `main-library-published` to Maven Local

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
## Using the Builder Pattern

In the app module, we utilize the builder pattern to create an instance of the main library and start its activity. Here's how it's done:

```groovy
val builder = AndroidLibrarySample.Builder()
        .setContext(this)
        .useAsLibrary(false)
        .build()

    builder.start()
    finish()
```
The Builder class in the `main-library-published` module provides a convenient way to set the necessary configurations for the library and start its activity.

## Starting the Main Library Activity

The `start()` method in the main library's AndroidLibrarySample class is responsible for launching the library's main activity. It uses an Intent to start the LibraryActivity:
```groovy
fun start() {
    Intent(context, LibraryActivity::class.java).apply {
        (context as Activity).startActivityForResult(this, REQUESTCODE)
    }
}
```
This allows users of the library to easily integrate it into their apps by simply calling the `start()` method.

## Publishing the Main Library
To publish the main library, we added the following configuration to all of the project's gradle files:
```groovy
publishing {
    repositories {
        maven {
            url = uri("$buildDir/repo")
        }
    }
    publications {
        register("Debug", MavenPublication::class) {
            pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")

                configurations.implementation.get().allDependencies.forEach { dependency ->
                    if (dependency.name != "unspecified") {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", dependency.group)
                        dependencyNode.appendNode("artifactId", dependency.name)
                        dependencyNode.appendNode("version", dependency.version)
                    }
                }
            }

            artifacts {
                val artifactFile = file("$buildDir/outputs/aar/${project.name}-debug.aar")
                artifact(artifactFile) {
                    artifactId = "${project.name}-debug"
                }
            }
        }
    }
}
```
This configuration allows us to publish the main library to the local Maven repository `(.m2)` on our computer. It also ensures that all dependencies are correctly resolved and included in the published library.
To publish the main library, we added the following configuration to all of the project's gradle files:

## POM & AAR

## POM (Project Object Model):
In the context of Maven-based projects (including Android projects that use Gradle with the maven-publish plugin), a POM file (Project Object Model) is an XML file that contains metadata about the project and its artifacts. It provides information such as the project's name, version, dependencies, developers, licenses, and more. The POM file is used to describe the project and its characteristics, making it easier for other projects or build systems to consume and understand the published artifacts.
In the Gradle script for publishing, you can customize the POM for your Android module using the pom.withXml block. This allows you to add or modify elements in the POM to provide additional information about your module when it is published to a Maven repository.pom.withXml is like this:
```groovy
       pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")

                configurations.implementation.get().allDependencies.forEach { dependency ->
                    if (dependency.name != "unspecified") {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", dependency.group)
                        dependencyNode.appendNode("artifactId", dependency.name)
                        dependencyNode.appendNode("version", dependency.version)
                    }
                }
            }
```
in this block we have:

1.**pom.withXml { ... }**:
This part of the code allows you to customize the POM (Project Object Model) for the published artifacts. The withXml block provides access to the XML representation of the POM, and you can manipulate it to add or modify elements.

2.**val dependenciesNode = asNode().appendNode("dependencies")**:
This line appends a new `<dependencies>` node to the POM XML. This node will hold the dependencies of the published artifact.

3.**configurations.implementation.get().allDependencies.forEach { dependency -> ... }**:
This loop iterates over all the dependencies of the current Android module. The configurations.implementation.get() represents the implementation configuration, which includes all the dependencies required for building the module
configurations.implementation.get().allDependencies.forEach { dependency -> ... } iterates over all the dependencies of the current Android module (dependent-library-two-debug) and filters out "unspecified" dependencies. Then, it customizes the POM by adding <dependency> elements under the <dependencies> section. As a result, the XML file includes information about the dependencies and their versions, and it will look like the provided XML:
```groovy
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>AndroidLibrarySample</groupId>
  <artifactId>dependent-library-two-debug</artifactId>
  <version>1.0.0</version>
  <packaging>aar</packaging>
  <dependencies>
    <dependency>
      <groupId>AndroidLibrarySample</groupId>
      <artifactId>dependent-library-one-debug</artifactId>
      <version>1.0.0</version>
    </dependency>
    <dependency>
      <groupId>AndroidLibrarySample</groupId>
      <artifactId>dependent-library-one-debug</artifactId>
      <version>1.0.0</version>
    </dependency>
    <dependency>
      <groupId>androidx.core</groupId>
      <artifactId>core-ktx</artifactId>
      <version>1.10.1</version>
    </dependency>
    <dependency>
      <groupId>androidx.appcompat</groupId>
      <artifactId>appcompat</artifactId>
      <version>1.6.1</version>
    </dependency>
     ...
    <!-- Other dependencies extracted from configurations.implementation.get().allDependencies -->
  </dependencies>
</project>
```
In the example provided, the POM file for the "dependent-library-two-debug" module includes several dependencies, both internal (e.g., "dependent-library-one-debug") and external (e.g., "androidx.core", "androidx.appcompat", etc.)
 and as you see in last code we have groupId and artifactId and version for enternal and external dependencies:
 
```groovy
dependencyNode.appendNode("groupId", dependency.group)
dependencyNode.appendNode("artifactId", dependency.name)
dependencyNode.appendNode("version", dependency.version)
```
for internal dependency like dependent-library-one:

  1. **groupId** : AndroidLibrarySample (it is rootProject.name  in android that we define it in setting.gradle)
  2. **artifactId** : dependent-library-one-debug
  3. **version** : 1.0.0

for internal dependency like androidx-appcompat:

   1. **groupId** : androidx.appcompat 
   2. **artifactId** : appcompat
   3. **version** : 1.6.1
      
## AAR(Android Archive)
An AAR (Android Archive) is a binary distribution format used by Android projects to package and distribute reusable Android libraries. It contains compiled code (JAR files), resources, and metadata required to use the library in an Android application.
After building the library module, Gradle will generate the AAR file in the `build/outputs/aar` directory of your library module
The `build` task  is responsible for building your Android application, including generating the AAR files for library modules, but it does not automatically publish them to a repository.
for publishing .aar we use of this code:

```groovy
   artifacts {
                val artifactFile = file("$buildDir/outputs/aar/${project.name}-debug.aar")
                artifact(artifactFile) {
                    artifactId = "${project.name}-debug"
                }
            }
```

the `artifacts` block is used to specify the AAR file that will be published to the local Maven repository.

1. **val artifactFile = file("$buildDir/outputs/aar/${project.name}-debug.aar")**: This line creates a variable artifactFile that represents the location of the AAR file that will be published. The file is located in the build directory of the module, inside the outputs/aar folder. The AAR file is named based on the project's name with the -debug.aar extension.

2. **artifact(artifactFile)**: This line specifies the AAR file that should be published. The artifact() function takes the artifactFile as an argument, indicating that this file should be included as part of the publication.

3. **artifactId = "${project.name}-debug"**: This line sets the artifactId property of the publication. The artifactId is a unique identifier for the artifact being published. In this case, it uses the project's name with the -debug suffix.

finally for useing enternal library in other library we should use like that in gradle of other library:
```groovy
implementation("AndroidLibrarySample:dependent-library-one-debug:1.0.0")
```
and if you search in your computer this library publish in this address:
`C:\Users\mozhgan.peivandian\.m2\repository\AndroidLibrarySample\dependent-library-one-debug\1.0.0`
in that path we can see this dependnecy:
`AndroidLibrarySample\dependent-library-one-debug\1.0.0`
in this address we have .aar and .pom files.

instead of that: 
```groovy
implementation(project(":dependent-library-one"))
```
Now, you have two options for using this library:

1. **Remote Repository (Recommended for sharing with others):** You can upload the published library to a remote repository, like a Git repository hosted on platforms like GitHub or a private artifact repository. By doing so, other developers or projects can easily include this library in their own projects by adding the appropriate dependency configuration to their build files.
dont forget for using this main-library-published in Remote Repository you also should add all of dependent libraries in Repository.

3. **Internal Library (For your own projects):** Alternatively, if you have multiple projects and want to reuse this library across them, you can keep the published library as a local dependency and use it internally within your projects. To do this, you can modify your project's build configuration to reference the local `.aar` file. For example, you can use `implementation files("path/to/main-library-published-debug.aar")` in your project's `build.gradle` file to include the library.

This README.md file provides instructions on how to use and publish the AndroidLibrarySample project with Maven locally. Follow the steps to create reusable libraries and utilize them in other projects. The project also demonstrates API data fetching using Retrofit and displaying it with Jetpack Compose. Happy coding!
