package com.example.nasaproject.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import org.apache.commons.codec.language.bm.Lang

class CustomSharedPreferences {

    companion object {

        private val PREFERENCES_TIME = "preferences_time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): CustomSharedPreferences = instance ?: synchronized(lock){
            instance?: makeSp(context).also {
                instance = it
            }
        }
        private fun makeSp(context: Context) : CustomSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time:Long){
        sharedPreferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }
    }
    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME,0)

}