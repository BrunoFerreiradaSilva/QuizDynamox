package com.example.quizdynamox.data.repository.quiz

import com.example.quizdynamox.data.service.QuizService
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.helpers.LoadingState
import com.example.quizdynamox.model.entity.AnswerRequest
import com.example.quizdynamox.model.entity.Question
import com.example.quizdynamox.model.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImp @Inject constructor(private val quizService: QuizService) : QuizRepository {
    override fun getQuestion(): Flow<DataState<Question>> = flow {
        emit(DataState.Loading(LoadingState.Loading))
        val questionDto = quizService.getQuestions()
        val question = Question(
            id = questionDto.id,
            statement = questionDto.statement,
            options = questionDto.options
        )

        emit(DataState.Data(data = question))
    }

    override fun sendSelectedOptions(
        idQuestion: Int,
        selectedOptionText: String
    ): Flow<DataState<Result>> = flow{
        emit(DataState.Loading(LoadingState.Loading))
        val answerRequest = AnswerRequest(selectedOptionText)
        val result = Result(
            result = quizService.sendQuestion(idQuestion, answerRequest).result
        )

        emit(DataState.Data(data = result))
    }
}