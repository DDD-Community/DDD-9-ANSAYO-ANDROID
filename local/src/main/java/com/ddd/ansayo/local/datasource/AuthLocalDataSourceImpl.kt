package com.ddd.ansayo.local.datasource

import android.content.Context
import android.content.SharedPreferences
import com.ddd.ansayo.data.AuthLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
   @ApplicationContext private val context: Context
): AuthLocalDataSource {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    private val prefsKey = "authToken"

    override var authToken: String
        get() = prefs.getString(prefsKey, "") ?: ""
        set(value) {
            prefs.edit().putString(prefsKey, value).apply()
        }
}
