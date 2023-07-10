package com.example.main_library_published

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dependent_library_one.data.Book
import com.example.dependent_library_two.ui.BookRepository


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookScreen(bookRepository: BookRepository) {
    var books = remember { mutableStateOf(emptyList<Book>()) }

    LaunchedEffect(Unit) {
        books.value = bookRepository.getAllBooks()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Book List") }) }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(books.value) { book ->
                Row {
                    Text(text = book.id.toString(), modifier = Modifier.padding(8.dp))
                    Text(text = book.title, modifier = Modifier.padding(8.dp))
                    Text(text = book.author, modifier = Modifier.padding(8.dp))
                    Text(text = book.summary, modifier = Modifier.padding(8.dp))

                }
            }
        }
    }
}