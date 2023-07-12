package com.example.convention

import java.text.SimpleDateFormat
import java.util.*

object BuildAndroidConfig {

    const val APPLICATION_ID = "ir.part.app.merat"
    const val LIBRARY_PACKAGE_NAME = "com.example"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val COMPILE_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 33
    const val SUPPORT_LIBRARY_VECTOR_DRAWABLES = true
    const val BUILD_TOOLS_VERSION = "33.0.0"
}


object Ext {
    const  val versionBusiness = 2 // versionBusiness rage only between  1 to 9
    const val versionMajor = 6
    const val versionMinor = 1
    const val versionPatch = 0
    const val minimumSdkVersion = 21
}

fun generateVersionCode(): Int {
    return Ext.minimumSdkVersion * 10000000 + Ext.versionBusiness * 1000000 + Ext.versionMajor * 10000 + Ext.versionMinor * 100 + Ext.versionPatch
}

fun generateVersionName(): String {
    return "${Ext.versionBusiness}.${Ext.versionMajor}.${Ext.versionMinor}.${Ext.versionPatch}"
}

@Suppress("SimpleDateFormat")
fun getDate(): String? {
     val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss",Locale.getDefault()).format(Date()) // you can change it
    return date
}

