package com.example.myfinance.di.module

import android.content.Context
import androidx.room.Room
import com.example.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "FinanceApp.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase) = appDatabase.transactionDao()

    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase) = appDatabase.categoryDao()

    @Provides
    @Singleton
    fun providePendingOperationDao(appDatabase: AppDatabase) = appDatabase.pendingOperationDao()
}