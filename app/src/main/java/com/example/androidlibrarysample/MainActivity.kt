package com.example.androidlibrarysample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidlibrarysample.ui.theme.AndroidLibrarySampleTheme
import com.example.main_library_published.AndroidLibrarySample

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            AndroidLibrarySampleTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }

        val builder = AndroidLibrarySample.Builder()
            .setContext(this)
            .useAsLibrary(false)
            .build()
        builder.start()
        finish()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidLibrarySampleTheme {
        Greeting("Android")
    }
}