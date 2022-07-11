package com.example.fastcalculatetraining.di

import com.example.fastcalculatetraining.data.repository_impl.RepositoryImpl
import com.example.fastcalculatetraining.domain.repository.Repository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @AppScope
    @Binds
    fun provideRepositoryImpl(repositoryImpl: RepositoryImpl): Repository

    companion object {

    }
}