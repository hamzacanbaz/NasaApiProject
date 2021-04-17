package com.example.nasaproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comment_table")

data class CommentsEntity(

    @PrimaryKey(autoGenerate = true)
    var commentId: Long = 0L,

    @ColumnInfo(name = "comment_user")
    var commentUser: String = "user",

    @ColumnInfo(name = "comment_title")
    var commentTitle: String = "title",

    @ColumnInfo(name = "comment_message")
    var commentMessage: String = "message",

    @ColumnInfo(name = "photo_id")
    var photoId: Int = 0
)

//@Entity(tableName = "photos")
//data class com.example.nasaproject.database.PhotosEntity(
//
//    @PrimaryKey(autoGenerate = true)
//    var imageId : Long = 0L,
//
//    @ColumnInfo(name = "img_src")
//    var img_src : String?=" ",
//
//    @ColumnInfo(name = "earth_date")
//    var earth_date : String?="",
//
//    /* @SerializedName("rover")
//     var rover : JsonObject,*/
//
//
//)