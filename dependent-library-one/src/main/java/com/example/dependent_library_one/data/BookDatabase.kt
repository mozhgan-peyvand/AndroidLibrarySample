package com.example.dependent_library_one.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
     abstract fun bookDao(): BookDao

    companion object {
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            if (INSTANCE == null) {
                synchronized(BookDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java, "book_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}