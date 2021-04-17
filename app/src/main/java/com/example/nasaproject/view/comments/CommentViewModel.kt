package com.example.nasaproject.view.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.nasaproject.database.entities.CommentsEntity
import com.example.nasaproject.database.DatabaseDao
import com.example.nasaproject.database.PhotoDao
import com.example.nasaproject.database.entities.PhotosEntity

class CommentViewModel(
    val id:Long,
    val position: Long,
    val photoSource : PhotoDao,
    val database: DatabaseDao,
    application: Application) : AndroidViewModel(application) {

    //val night = database.insert(comment)

    val readAllData : LiveData<List<CommentsEntity>> = database.getAllComments()

    val getPhoto :  LiveData<PhotosEntity> = photoSource.getPhoto(id)

    val getComment : LiveData<List<CommentsEntity>> = database.getComment(key = position.toLong())


}
