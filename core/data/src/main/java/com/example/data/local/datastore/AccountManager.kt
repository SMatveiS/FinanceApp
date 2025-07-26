package com.example.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

val Context.accountDataStore: DataStore<Preferences> by preferencesDataStore(name = "account")

@Singleton
class AccountManager @Inject constructor(private val context: Context) {

    companion object {
        private val ACCOUNT_KEY = stringPreferencesKey("account_data")
    }

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }


    private val accountFlow: Flow<Account?> = context.accountDataStore.data
        .map { preferences ->
            preferences[ACCOUNT_KEY]?.let { jsonString ->
                try {
                    json.decodeFromString<Account>(jsonString)
                } catch (e: SerializationException) {
                    null
                }
            }
        }

    suspend fun getAccount(): Account? {
        return accountFlow.firstOrNull()
    }

    suspend fun updateAccount(account: Account) {
        context.accountDataStore.edit { preferences ->
            preferences[ACCOUNT_KEY] = json.encodeToString(account)
        }
    }
}