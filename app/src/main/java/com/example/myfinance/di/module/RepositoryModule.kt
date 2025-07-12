package com.example.myfinance.di.module

import com.example.myfinance.data.api.account.AccountRepositoryImpl
import com.example.myfinance.data.api.category.CategoryRepositoryImpl
import com.example.myfinance.data.api.transaction.TransactionRepositoryImpl
import com.example.myfinance.domain.repository.AccountRepository
import com.example.myfinance.domain.repository.CategoryRepository
import com.example.myfinance.domain.repository.TransactionRepository
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

}