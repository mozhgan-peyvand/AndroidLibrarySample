package com.example.convention

class BuildModules {
    enum class Libraries(val libraryValue: String, val implementationValue: String) {
        DependentLibraryOne (":dependent-library-one","${BuildAndroidConfig.LIBRARY_PACKAGE_NAME}:dependent-library-one"),
        DependentLibraryTwo (":dependent-library-two","${BuildAndroidConfig.LIBRARY_PACKAGE_NAME}:dependent-library-two"),
        MainLibraryPublished (":main-library-published","${BuildAndroidConfig.LIBRARY_PACKAGE_NAME}:main-library-published"),
    }
}


