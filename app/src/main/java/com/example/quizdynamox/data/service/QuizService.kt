package com.example.quizdynamox.data.service

import com.example.quizdynamox.model.dto.QuestionDTO
import retrofit2.http.GET

interface QuizService {
    @GET("/question")
    suspend fun getQuestions(): QuestionDTO


}