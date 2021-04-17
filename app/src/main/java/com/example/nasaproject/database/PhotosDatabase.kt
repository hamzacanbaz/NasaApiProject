//package com.example.nasaproject.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import kotlinx.coroutines.InternalCoroutinesApi
//
//@Database(entities = [com.example.nasaproject.database.PhotosEntity::class], version = 1, exportSchema = false)
//abstract class PhotosDatabase : RoomDatabase() {
//
//    abstract val photoDao: PhotoDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: PhotosDatabase? = null
//
//        @InternalCoroutinesApi
//        fun getInstance(context: Context): PhotosDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        PhotosDatabase::class.java,
//                        "photos_table"
//                    ).allowMainThreadQueries()
//                        //allow main thread sil ve başka yerde hallet
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//
//
//}