package com.example.myfinance.data.local.datastore

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myfinance.domain.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
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


    val accountFlow: Flow<Account?> = context.accountDataStore.data
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
        return accountFlow.firstOrNull() ?: run {
            // Можно добавить дополнительную логику, например попытку загрузки из сети
            null
        }
    }

    suspend fun saveAccount(account: Account) {
        context.accountDataStore.edit { preferences ->
            preferences[ACCOUNT_KEY] = json.encodeToString(account)
        }
    }
}

//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "account")
//
//class DataStoreManager(private val context: Context) {
//
//    private val ACCOUNT_KEY = stringPreferencesKey("account_key")
//
//    suspend fun setAccount(account: Account) {
//
//    }
//
//    suspend fun <T> save(key: Preferences.Key<T>, value: T) {
//        context.dataStore.edit { preferences ->
//            preferences[key] = value
//        }
//    }
//
//    fun <T> read(key: Preferences.Key<T>): Flow<T?> = context.dataStore.data
//        .map { settings ->
//            settings[key]
//        }
//
//    private val ACCOUNT_ID_KEY = intPreferencesKey("account_id_key")
//    suspend fun setAccountId(value: Int) = save(ACCOUNT_ID_KEY, value)
//    fun getAccountId(): Flow<Int?> = read(ACCOUNT_ID_KEY)
//
//    private val ACCOUNT_NAME_KEY = stringPreferencesKey("account_name_key")
//    suspend fun setAccountName(value: String) = save(ACCOUNT_NAME_KEY, value)
//    fun getAccountName(): Flow<String?> = read(ACCOUNT_NAME_KEY)
//
//    private val ACCOUNT_CURRENCY_KEY = stringPreferencesKey("account_currency_key")
//    suspend fun setAccountCurrency(value: String) = save(ACCOUNT_CURRENCY_KEY, value)
//    fun getAccountCurrency(): Flow<String?> = read(ACCOUNT_CURRENCY_KEY)
//
//    private val ACCOUNT_BALANCE_KEY = doublePreferencesKey("account_balance_key")
//    suspend fun setAccountBalance(value: Double) = save(ACCOUNT_BALANCE_KEY, value)
//    fun getAccountBalance(): Flow<Double?> = read(ACCOUNT_BALANCE_KEY)
//
//    suspend fun updateAccount(account: Account) {
//        setAccountId(account.id)
//        setAccountName(account.name)
//        setAccountBalance(account.balance)
//        setAccountCurrency(account.currency)
//    }
//
//    fun getAccount(): Account {
//        return Account(
//            id = getAccountId().collectAsState().value,
//            name = getAccountName(),
//            balance = getAccountBalance(),
//            currency = getAccountCurrency()
//        )
//    }
//}

//object AccountSerializer : Serializer<Account?> {
//    override val defaultValue: Account? = null
//
//    override suspend fun readFrom(input: InputStream): Account? {
//        return try {
//            Json.decodeFromString(
//                Account.serializer(),
//                input.readBytes().decodeToString()
//            )
//        } catch (e: SerializationException) {
//            defaultValue
//        }
//    }
//
//    override suspend fun writeTo(t: Account?, output: OutputStream) {
//        output.write(
//            Json.encodeToString(Account.serializer(), t)
//                .encodeToByteArray()
//        )
//    }
//}