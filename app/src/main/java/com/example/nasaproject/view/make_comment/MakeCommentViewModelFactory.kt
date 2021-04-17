package com.example.nasaproject.view.make_comment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaproject.database.DatabaseDao

class MakeCommentViewModelFactory(
    private val dataSource: DatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MakeCommentViewModel::class.java)){
            return MakeCommentViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown")
    }

}