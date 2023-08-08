package com.ddd.ansayo.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = UserPref.DATA_STORE_NAME)

class UserPref @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        const val DATA_STORE_NAME = "dataStore"

        private val KEY_ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        private val KEY_TOKEN_TYPE = stringPreferencesKey("key_token_type")
    }

    private val dataStore = context.dataStore

    private suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }

    private suspend fun <T> get(key: Preferences.Key<T>) =
        dataStore.data.map { it[key] }.first()

    suspend fun setAccessToken(accessToken: String) = set(KEY_ACCESS_TOKEN, accessToken)

    suspend fun getAccessToken(): String = get(KEY_ACCESS_TOKEN) ?: ""

    suspend fun setTokenType(tokenType: String) = set(KEY_TOKEN_TYPE, tokenType)

    suspend fun getTokenType(): String = get(KEY_TOKEN_TYPE) ?: ""


}