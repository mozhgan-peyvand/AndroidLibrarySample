package util

object Versions {
    private const val versionBusiness = 2 // versionBusiness rage only between  1 to 9
    private const val versionMajor = 6
    private const val versionMinor = 1
    private const val versionPatch = 0
    private const val minimumSdkVersion = 21
    const val versionCode =
        minimumSdkVersion * 10000000 + versionBusiness * 1000000 + versionMajor * 10000 + versionMinor * 100 + versionPatch
    const val versionName =
        "$versionBusiness.$versionMajor.$versionMinor.$versionPatch"
    const val buildTools = "33.0.0"
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
}