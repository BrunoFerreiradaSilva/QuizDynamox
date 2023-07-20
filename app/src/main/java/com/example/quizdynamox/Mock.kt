package com.example.quizdynamox


data class Question(
    val id: Int,
    val statement: String,
    val options: List<String>,
    val isFinalQuestion: Boolean,
    val response: String
)

fun firstQuestion(value: String): Question {
    return Question(
        id = 1,
        statement = "Qual é a melhor tec mobile?",
        options = listOf("iOS", "Flutter", "React Nativa", "Android"),
        isFinalQuestion = false,
        response = value
    )
}

fun secondQuestion(value: String): Question {
    return Question(
        id = 2,
        statement = "Qual é a melhor tec web?",
        options = listOf("React", "Angular", "Vue", "Sei la"),
        isFinalQuestion = false,
        response = value
    )
}

fun question(value: String): Question {
    return Question(
        id = 3,
        statement = "Qual é a melhor Time?",
        options = listOf("Flamengo", "Vasco", "Fluminense", "Botafogo"),
        isFinalQuestion = false,
        response = value
    )
}

fun finalQuestion(value: String): Question {
    return Question(
        id = 4,
        statement = "",
        options = listOf(),
        isFinalQuestion = true,
        response = value
    )
}

fun getQuestions(question: Int): Question {
    return when (question) {
        0 -> firstQuestion("Android")
        1 -> secondQuestion("React")
        2 -> question("Flamengo")
        else -> finalQuestion("")
    }
}

