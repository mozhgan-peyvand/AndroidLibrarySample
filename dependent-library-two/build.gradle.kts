import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("maven-publish")
}


android {
    namespace = "com.example.dependent_library_two"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
//    addDependency(
//        listOf(
//
//        )
//    )
    implementation(project(":dependent-library-one"))

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(libs.activityCompose)
    implementation(libs.composeCompiler)
    implementation(libs.composeFoundation)
    implementation(libs.composeMaterial)
    implementation(libs.composeRuntime)
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiTooling)

    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)
    implementation(libs.roomKtx)
}

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

                // Add dependencies on library one and library two
                val libraryOneDependency = dependenciesNode.appendNode("dependency")
                libraryOneDependency.appendNode(
                    "groupId",
                    "com.example.dependent_library_one"
                ) // Customize with the actual group ID of library one
                libraryOneDependency.appendNode(
                    "artifactId",
                    "library-one"
                ) // Customize with the actual artifact ID of library one
                libraryOneDependency.appendNode(
                    "version",
                    "1.0.0"
                ) // Customize with the actual version of library one
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
