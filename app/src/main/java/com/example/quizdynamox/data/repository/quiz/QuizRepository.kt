package com.example.quizdynamox.data.repository.quiz

import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.model.entity.AnswerRequest
import com.example.quizdynamox.model.entity.Question
import com.example.quizdynamox.model.entity.Result
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuestion(): Flow<DataState<Question>>
    fun sendSelectedOptions(idQuestion: Int, selectedOptionText: String): Flow<DataState<Result>>
}