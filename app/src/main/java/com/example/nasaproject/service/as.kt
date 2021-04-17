/*package com.example.nasaproject.service

package com.example.nasaproject.view.main

import android.app.Application
import android.util.JsonReader
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.nasaproject.NasaModel
import com.example.nasaproject.database.CommentsEntity
import com.example.nasaproject.database.DatabaseDao
import com.example.nasaproject.service.NasaApi
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.StringReader
import java.lang.Exception

enum class NasaApiStatus{ LOADING, ERROR, DONE }

class MainViewModel(val dataSource: DatabaseDao, application: Application) : ViewModel() {
    private lateinit var asd : LiveData<List<CommentsEntity>>
    val BASE_URL = "https://api.nasa.gov"

    private val _status = MutableLiveData<NasaApiStatus>()
    val status : LiveData<NasaApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<NasaModel>>()

    val properties: LiveData<List<NasaModel>>
        get() = _properties

    init {
        getNasaProperties()
    }

    private fun getNasaProperties() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaApi::class.java)

        viewModelScope.launch {
            _status.value=NasaApiStatus.LOADING
            try {
                _properties.value = retrofit.getData()
                _status.value = NasaApiStatus.DONE
            }
            catch (e:Exception){
                _status.value = NasaApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    //val readAllData : LiveData<List<NasaModel>>
    /* fun loadData(){
         println("xx")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaApi::class.java)

        val job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getData()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    //println(response.body())
                    response.body()?.get("photos")?.asJsonArray?.let { println(it.size()) }
                    println(
                        response.body()?.get("photos")?.asJsonArray?.get(1)?.asJsonObject?.get("id")
                            .toString())
                }
            }
        }
    }*/
    /*fun getobjectfromjson(jsonStr : String) : List<NasaModel>{
        var stringReader: StringReader = StringReader(jsonStr)
        var jsonReader: JsonReader = JsonReader(stringReader)

        val gsonBuilder = GsonBuilder().serializeNulls()
        gsonBuilder.registerTypeAdapter(NasaModel::class.java,WeatherDeserializer())
        val gson = gsonBuilder.create()

        val nasaList: List<NasaModel> = gson.fromJson(stringReader , Array<NasaModel>::class.java).toList()

        return nasaList
    }*/




}*/