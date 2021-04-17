package com.example.nasaproject.view.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaproject.database.DatabaseDao
import com.example.nasaproject.database.PhotoDao

class MainViewModelFactory (
        private val photoSource: PhotoDao,
        private val dataSource: DatabaseDao,
        private val application: Application
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(photoSource,dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown")
    }

}