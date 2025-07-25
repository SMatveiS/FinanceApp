package com.example.data.di

import com.example.data.repository.external.AccountRepository
import com.example.data.repository.external.CategoryRepository
import com.example.data.repository.external.ThemeRepository
import com.example.data.repository.external.TransactionRepository
import com.example.data.repository.internal.AccountRepositoryImpl
import com.example.data.repository.internal.CategoryRepositoryImpl
import com.example.data.repository.internal.ThemeRepositoryImpl
import com.example.data.repository.internal.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Модуль для связи репозиториев в доменнном слое и их реализаций в слое данных
 */

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Singleton
    @Binds
    fun provideCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Singleton
    @Binds
    fun provideTransactionRepository(transactionRepositoryImpl: TransactionRepositoryImpl): TransactionRepository

    @Singleton
    @Binds
    fun provideThemeRepository(themeRepositoryImpl: ThemeRepositoryImpl): ThemeRepository

}