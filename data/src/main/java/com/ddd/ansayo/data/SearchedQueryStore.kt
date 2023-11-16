package com.ddd.ansayo.data

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SearchedQueryStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val searchMaxCount = 14
    private val prefsRecentSearchKey = "recentSearch"

    private val prefs: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    private fun saveRecentSearchText(jsonString: String?) = prefs.edit()
        .putString(prefsRecentSearchKey, jsonString)
        .apply()

    fun getTexts(): List<String> {
        val result = prefs.getString(prefsRecentSearchKey, null)

        return if (result != null && !TextUtils.isEmpty(result)) {
            val listType = object : TypeToken<ArrayList<String?>?>() {}.type
            Gson().fromJson(result, listType)
        } else {
            ArrayList()
        }
    }

    private fun toJson(texts: List<String>?): String? {
        if (texts == null) return null
        val array = JsonArray()
        for (text in texts) {
            val t = JsonPrimitive(text)
            array.add(t)
        }
        return Gson().toJson(array)
    }

    fun addText(text: String) {
        val oldTexts: MutableList<String> = ArrayList(getTexts())

        if (!oldTexts.contains(text)) {
            if (oldTexts.size >= searchMaxCount) {
                oldTexts.removeAt(oldTexts.size - 1)
            }
        } else {
            for (s in oldTexts) {
                if (s == text) {
                    oldTexts.remove(s)
                    break
                }
            }
        }
        oldTexts.add(0, text)

        val jsonString = toJson(oldTexts)
        saveRecentSearchText(jsonString)
    }

    fun removeText(text: String) {
        val oldTexts = getTexts().toMutableList()
        oldTexts.remove(text)
        saveRecentSearchText(toJson(oldTexts))
    }
}
