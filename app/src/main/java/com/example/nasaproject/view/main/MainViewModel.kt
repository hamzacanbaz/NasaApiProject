package com.example.nasaproject.view.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.nasaproject.database.*
import com.example.nasaproject.database.entities.PhotosEntity
import com.example.nasaproject.service.NasaApi
import com.example.nasaproject.util.CustomSharedPreferences
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel(
    val photoDao: PhotoDao,
    val dataSource: DatabaseDao,
    application: Application,
) : AndroidViewModel(application) {

    val BASE_URL = "https://api.nasa.gov"

    //var nasa : ArrayList<PhotosEntity> = ArrayList()
    private var customSharedPreferences = CustomSharedPreferences(context = getApplication())

    //    var nasa_objects = MutableLiveData<ArrayList<PhotosEntity>>()
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L


    var readAllData: LiveData<List<PhotosEntity>> = photoDao.getPhotos()


    @InternalCoroutinesApi
    fun refreshData() {
        val updateTime = customSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQlite()
            //loadData()
        } else {
            loadData()
        }
    }


    private fun getDataFromSQlite() {
        Toast.makeText(getApplication(),"From SQLite",Toast.LENGTH_LONG).show()
        println("sqlitetan çekildi")
        readAllData = photoDao.getPhotos()
    }


    @InternalCoroutinesApi
     fun loadData() {
        Toast.makeText(getApplication(),"From API",Toast.LENGTH_LONG).show()

        println("apiden cekildi")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaApi::class.java)


        viewModelScope.launch {
            val response = retrofit.getData()
            //nasa.clear()
            if (response.isSuccessful) {
                photoDao.deleteAllPhotos()
                //response.body()?.let { storeInSQLite(it) }
                //  println(response.body())
                //  response.body()?.get("photos")?.asJsonArray?.let { println(it.size()) }

                for (i in 0 until (response.body()?.get("photos")?.asJsonArray?.size()!!)) {
                    var earth_date = response.body()
                        ?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("earth_date")
                        .toString()
                    var launch = response.body()
                        ?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("rover")?.asJsonObject?.get("launch_date")
                        .toString()
                    var landing = response.body()
                        ?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("rover")?.asJsonObject?.get("landing_date")
                        .toString()
                    var name = response.body()
                        ?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("rover")?.asJsonObject?.get("name")
                        .toString()
                    var img_src = response.body()
                        ?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("img_src")
                        .toString()

                    /* var m = response.body()?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("rover")?.asJsonObject
                             .toString()*/
                    //println("url"+k)
                    var nasaModel = PhotosEntity(img_src = img_src, earth_date = earth_date,launch_date = launch,
                    landing_date = landing,rover_name = name)
                    //nasa.add(nasaModel)
                    storeInSQLite(nasaModel)
                }
            }
//             nasa_objects.value = nasa
        }
    }

    @InternalCoroutinesApi
    private fun storeInSQLite(response: PhotosEntity) {
        viewModelScope.launch {
            photoDao.insertAll(response)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }


}