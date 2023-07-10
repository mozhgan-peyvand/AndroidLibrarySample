package com.example.dependent_library_two.ui

import androidx.room.withTransaction
import com.example.dependent_library_one.data.Book
import com.example.dependent_library_one.data.BookDao
import com.example.dependent_library_one.data.BookDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(
    private val bookDao: BookDao,
    private val bookDatabase: BookDatabase
    ) {
    suspend fun insert(book: List<Book>) {

        bookDatabase.withTransaction {
            delete()
            bookDao.insert(book = book)
        }
    }

    fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }

    suspend fun getAllBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            bookDao.getAllBooks()
        }
    }

    private suspend fun delete() {
        withContext(Dispatchers.IO){
            bookDao.deleteAllBooks()
        }
    }
}