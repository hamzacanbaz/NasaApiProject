package com.example.nasaproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nasaproject.NasaModel

@Dao
interface PhotoDao {

   @Insert
    suspend fun insertAll( photos: PhotosEntity)
//  @Insert
//  fun insert(photo : PhotosEntity)


    @Query("SELECT * FROM photos ")
     fun getPhotos() : LiveData<List<PhotosEntity>>

    @Query("DELETE FROM photos")
    suspend fun deleteAllPhotos()


    @Query("SELECT * FROM photos WHERE imageId=:id")
     fun getPhoto(id:Long) : LiveData<PhotosEntity>

}