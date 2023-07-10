package com.example.main_library_published

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.example.dependent_library_one.data.Book
import com.example.dependent_library_one.data.BookDatabase
import com.example.dependent_library_two.ui.BookRepository


class LibraryActivity : ComponentActivity() {
    private lateinit var bookRepository: BookRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookRepository = BookRepository(BookDatabase.getInstance(this).bookDao(), bookDatabase = BookDatabase.getInstance(this))
        setContent {

            LaunchedEffect(key1 = Unit){
                bookRepository.insert(
                    listOf(
                        Book(
                            id = 5,
                            author = "mozhgan",
                            summary = "hi im books 5",
                            title = "life book 5"
                        ),
                        Book(
                            id = 6,
                            author = "mozhgan",
                            summary = "hi im books 6",
                            title = "life book 6"
                        ),
                        Book(
                            id = 7,
                            author = "mozhgan",
                            summary = "hi im books 7",
                            title = "life book 7"
                        ),
                        Book(
                            id = 8,
                            author = "mozhgan",
                            summary = "hi im books 8",
                            title = "life book 8"
                        )
                    )

                )
            }
            BookScreen(bookRepository = bookRepository)
        }
    }
}