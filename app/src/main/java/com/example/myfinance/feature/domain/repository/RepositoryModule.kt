package com.example.myfinance.feature.domain.repository

import com.example.myfinance.data.api.account.AccountRemoteDataSource
import com.example.myfinance.data.api.account.AccountRepositoryImpl
import com.example.myfinance.data.api.category.CategoryRemoteDataSource
import com.example.myfinance.data.api.category.CategoryRepositoryImpl
import com.example.myfinance.data.api.transaction.TransactionRemoteDataSource
import com.example.myfinance.data.api.transaction.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAccountRepository(remoteDataSource: AccountRemoteDataSource): AccountRepository {
        return AccountRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(remoteDataSource: CategoryRemoteDataSource): CategoryRepository {
        return CategoryRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(remoteDataSource: TransactionRemoteDataSource): TransactionRepository {
        return TransactionRepositoryImpl(remoteDataSource)
    }
}