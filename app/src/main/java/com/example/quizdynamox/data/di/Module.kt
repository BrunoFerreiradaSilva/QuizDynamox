package com.example.quizdynamox.data.di

import com.example.quizdynamox.data.repository.QuizRepository
import com.example.quizdynamox.data.repository.QuizRepositoryImp
import com.example.quizdynamox.data.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesUserQuizRepository(
        service: Service
    ): QuizRepository {
        return QuizRepositoryImp(service)
    }



}