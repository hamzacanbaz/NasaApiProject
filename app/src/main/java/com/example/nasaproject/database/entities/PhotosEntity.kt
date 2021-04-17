package com.example.nasaproject.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject

@Entity(tableName = "photos")
data class PhotosEntity(

    @PrimaryKey(autoGenerate = true)
    var imageId : Long = 0L,

    @ColumnInfo(name = "img_src")
    var img_src : String?=" ",

    @ColumnInfo(name = "earth_date")
    var earth_date : String?=" ",

    @ColumnInfo(name = "launch_date")
    var launch_date : String?=" ",

    @ColumnInfo(name = "landing_date")
    var landing_date : String?=" ",

    @ColumnInfo(name = "rover_name")
    var rover_name : String?=" ",




)