package com.example.nasaproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nasaproject.database.entities.CommentsEntity

@Dao
interface DatabaseDao {

    @Insert
    fun insert(comment : CommentsEntity)

    @Update
    fun update(comment : CommentsEntity)

    @Query("SELECT * from comment_table WHERE commentId = :key")
    fun get(key:Long) : CommentsEntity?

    @Query("DELETE FROM comment_table")
    fun delete()

    @Query("SELECT * FROM comment_table ORDER BY commentId DESC LIMIT 1")
    fun getLast() : CommentsEntity?

    @Query("SELECT * FROM comment_table ORDER BY commentId DESC")
     fun getAllComments(): LiveData<List<CommentsEntity>>

    @Query("SELECT * from comment_table WHERE photo_id = :key ")
    fun getComment(key:Long) : LiveData<List<CommentsEntity>>


   /* @Query("INSERT INTO photos VALUES (:photos) ")
   suspend fun insertAll(vararg photos: NasaModel) : List<Long>*/




}