package com.example.mvvmsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmsampleapp.data.db.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDAO

    // to create AppDatabase
    // object is like singleton
    // companion like static
    companion object {
        //volatile annotaion means the variable is immediately visible for all other threads
        @Volatile
        private var instance: AppDatabase? = null
        //We use the LOCK variable To make sure ,two instances of our database are not created
        private val LOCK = Any()
        //invoke function will immediately return the instance if not null else if will execute the
        // synchronized block
        // ?: is the elvis operator

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase.db "
        ).build()

    }
}