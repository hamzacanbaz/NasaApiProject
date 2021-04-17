package com.example.nasaproject.view.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaproject.database.DatabaseDao
import com.example.nasaproject.database.PhotoDao

class CommentViewModelFactory (
    private val id : Long,
    private val position : Long,
    private val photoSource : PhotoDao,
    private val dataSource: DatabaseDao,
    private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)){
            return CommentViewModel(id,position.toLong(),photoSource,dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown")
    }

}