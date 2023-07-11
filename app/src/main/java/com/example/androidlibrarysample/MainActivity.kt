package com.example.androidlibrarysample

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.main_library_published.AndroidLibrarySample

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder = AndroidLibrarySample.Builder()
            .setContext(this)
            .useAsLibrary(false)
            .build()

        builder.start()
        finish()
    }
}