package com.ddd.ansayo.local.preference

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    private val prefsKey = "authToken"

    var authToken : String?
        get() = prefs.getString(prefsKey, "")
        set(value) = prefs.edit().putString(prefsKey, value).apply()

}