plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("maven-publish")
}

android {
    namespace = "com.example.main_library_published"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
//    implementation(project(":dependent-library-two"))
//    implementation(project(":dependent-library-one"))
    implementation("AndroidLibrarySample:dependent-library-one-debug:unspecified")
    implementation("AndroidLibrarySample:dependent-library-two-debug:unspecified")

    implementation(libs.androidCore)
    implementation(libs.appCompat)
    implementation(libs.androidMaterial)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.activityCompose)
    implementation(libs.composeCompiler)
    implementation(libs.composeFoundation)
    implementation(libs.composeMaterial)
    implementation(libs.composeRuntime)
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiTooling)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.testJuit)
    androidTestImplementation(libs.espressoCore)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)
}

publishing {
    repositories {
        maven {
            url = uri("$buildDir/repo")
        }
    }
    publications {
        create<MavenPublication>("Debug") {
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
