
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
    implementation("AndroidLibrarySample:dependent-library-one-debug:unspecified")
//    implementation(project(":dependent-library-one"))

    implementation(libs.androidCore)
    implementation(libs.appCompat)
    implementation(libs.androidMaterial)
    implementation(libs.activityCompose)
    implementation(libs.composeCompiler)
    implementation(libs.composeFoundation)
    implementation(libs.composeMaterial)
    implementation(libs.composeRuntime)
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiTooling)

    testImplementation(libs.junit)
    androidTestImplementation(libs.testJuit)
    androidTestImplementation(libs.espressoCore)

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
