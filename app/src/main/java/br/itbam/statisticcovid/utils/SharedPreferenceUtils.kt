package br.itbam.statisticcovid.utils

import android.content.Context
import android.content.SharedPreferences


class SharedPreferenceUtils(context: Context) {
    private val preferenceKey = "preference_key"
    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)
    }

    fun saveStringInPreference(key: String, value: String){
       sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun getPreferenceString(key: String) = sharedPreferences.getString(key, null)

}