package com.example.quizdynamox.data.repository.quiz

import com.example.quizdynamox.data.service.QuizService
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.helpers.LoadingState
import com.example.quizdynamox.model.entity.AnswerRequest
import com.example.quizdynamox.model.entity.Question
import com.example.quizdynamox.model.entity.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImp @Inject constructor(private val quizService: QuizService) : QuizRepository {
    override fun getQuestion(): Flow<DataState<Question>> = flow {
        emit(DataState.Loading(LoadingState.Loading))
        try {
            val questionDto = quizService.getQuestions()
            val question = Question(
                id = questionDto.id,
                statement = questionDto.statement,
                options = questionDto.options
            )
            emit(DataState.Data(data = question))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }

    override fun sendSelectedOption(
        questionId: Int,
        selectedOptionText: String
    ): Flow<DataState<ResultResponse>> = flow {
        try {
            val answerRequest = AnswerRequest(selectedOptionText)
            val result = quizService.sendQuestion(questionId, answerRequest)

            emit(DataState.Data(data = result))
        } catch (error: Exception) {

            emit(DataState.Error(error = error))
        }

    }
}