package com.example.quizdynamox.data.di

import android.content.Context
import com.example.quizdynamox.BASE_URL
import com.example.quizdynamox.data.database.PlayerDAO
import com.example.quizdynamox.data.database.QuizDataBase
import com.example.quizdynamox.data.repository.player.PlayerRepository
import com.example.quizdynamox.data.repository.player.PlayerRepositoryImp
import com.example.quizdynamox.data.repository.quiz.QuizRepository
import com.example.quizdynamox.data.repository.quiz.QuizRepositoryImp
import com.example.quizdynamox.data.service.QuizService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesUserQuizRepository(
        quizService: QuizService
    ): QuizRepository {
        return QuizRepositoryImp(quizService)
    }

    @Provides
    fun providesPlayerRepository(playerDAO: PlayerDAO): PlayerRepository {
        return PlayerRepositoryImp(playerDAO)
    }

    @Provides
    fun providesDAO(@ApplicationContext appContext: Context): PlayerDAO {
        return QuizDataBase.getDatabase(appContext).playerDAO()
    }

    @Provides
    fun providesRetrofit(): QuizService {
        val client = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                client
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }
}