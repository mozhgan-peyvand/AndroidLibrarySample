package com.example.dependent_library_one.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Insert
    fun insert(book: List<Book>)

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: Int): Book?

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>

    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()
}