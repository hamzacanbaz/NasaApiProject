package com.example.nasaproject.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface NasaApi {

    @GET("/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2020-6-3&api_key=Ad7aNpRu51HIdAe266Zfmv5yZytCU4HoKl0uYWXV&page=1")
    suspend fun getData() : Response<JsonObject>
}