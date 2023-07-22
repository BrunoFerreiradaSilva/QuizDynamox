package com.example.quizdynamox.data.service

import com.example.quizdynamox.model.dto.QuestionDTO
import com.example.quizdynamox.model.entity.AnswerRequest
import com.example.quizdynamox.model.entity.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuizService {
    @GET("/question")
    suspend fun getQuestions(): QuestionDTO

    @POST("/answer?")
    suspend fun sendQuestion(
        @Query("questionId") questionId: Int,
        @Body answerRequest: AnswerRequest
    ): Result
}