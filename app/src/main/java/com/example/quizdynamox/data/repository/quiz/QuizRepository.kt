package com.example.quizdynamox.data.repository.quiz

import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.Question
import com.example.quizdynamox.model.entity.ResultResponse
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuestion(): Flow<DataState<Question>>
    fun sendSelectedOption(questionId: Int, selectedOptionText: String): Flow<DataState<ResultResponse>>
}