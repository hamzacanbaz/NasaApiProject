package com.example.nasaproject.models

import android.util.JsonReader
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.*
import com.google.gson.annotations.SerializedName
import java.io.StringReader
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.HashMap


data class NasaModel(

    @SerializedName("img_src")
    var img_src : String?,

    @SerializedName("earth_date")
    var earth_date : String?,



){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}


//veri tuttuğumuz sınıflar