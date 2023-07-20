package com.example.quizdynamox.data.service

import com.example.quizdynamox.Question
import retrofit2.http.GET

interface Service {
    @GET
    suspend fun getQuestions(): Question
}