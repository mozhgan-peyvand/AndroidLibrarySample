package com.example.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate

fun Project.addDependency(
    libraryNames: List<BuildModules.Libraries>
) {
    val usePublishDependencyInGradle: String by project
    dependencies {
        libraryNames.forEach { libraryName ->
            if (usePublishDependencyInGradle == "true") {

                add(
                    "Implementation",
                    libraryName.implementationValue + "-debug                                   :"
                )
            } else
                add(
                    "implementation",
                    project(mapOf("path" to libraryName.libraryValue))
                )
        }
    }
}