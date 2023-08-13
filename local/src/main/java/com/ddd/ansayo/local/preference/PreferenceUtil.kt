package com.ddd.ansayo.local.preference

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun getAuthToken(key: String,defValue: String): String {
       return prefs.getString(key,defValue).toString()
    }

    fun setAuthToken(key: String,defValue: String): String {
        return prefs.edit().putString(key,defValue).toString()
    }

    fun getAuthTokenType(key: String,defValue: String): String {
        return prefs.getString(key,defValue).toString()
    }

    fun setAuthTokenType(key: String,defValue: String): String {
        return prefs.edit().putString(key,defValue).toString()
    }
}