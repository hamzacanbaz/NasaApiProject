package com.example.nasaproject.view.make_comment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nasaproject.database.entities.CommentsEntity
import com.example.nasaproject.database.DatabaseDao
import kotlinx.coroutines.launch

class MakeCommentViewModel(val database: DatabaseDao, application: Application) : AndroidViewModel(application) {

    var username : MutableLiveData<String> = MutableLiveData()
    var title : MutableLiveData<String> = MutableLiveData()
    var comment : MutableLiveData<String> = MutableLiveData()
    var photo_id : MutableLiveData<Int> = MutableLiveData()

 fun insertData(name : String, title:String, message : String, photoId:Int){

        viewModelScope.launch {
            database.insert(comment = CommentsEntity(commentTitle = title,commentUser = name,commentMessage = message,photoId = photoId))
        }

    }
}