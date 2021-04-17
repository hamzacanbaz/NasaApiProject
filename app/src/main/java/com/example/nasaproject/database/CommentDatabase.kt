package com.example.nasaproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [CommentsEntity::class, PhotosEntity::class], version = 4, exportSchema = false)
abstract class CommentDatabase : RoomDatabase() {

    abstract val commentDao: DatabaseDao
    abstract val photoDao: PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: CommentDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): CommentDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CommentDatabase::class.java,
                        "sleep_history_database"
                    ).allowMainThreadQueries()
                        //allow main thread sil ve başka yerde hallet
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
