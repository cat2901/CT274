package com.example.ttquizapp

object Constants {
    const val USR_NAME = "username"

    const val CORRECT_ANS = "correct_answer"
    const val TOTAL_QUESTION = "total_question"

    fun getQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val q1 = Question(
            id = 1,
            question = "What country does this flag belong to?",
            image = R.drawable.ic_flag_of_argentina,
            opt1 = "Argentina",
            opt2 = "Armenia",
            opt3 = "Australia",
            opt4 = "Austria",
            correctAnswer = 1
        )
        questionList.add(q1)

        val q2 = Question(
            id = 2,
            question = "What country does this flag belong to?",
            image = R.drawable.ic_flag_of_brazil,
            opt1 = "Argentina",
            opt2 = "Brazil",
            opt3 = "Australia",
            opt4 = "Austria",
            correctAnswer = 2
        )
        questionList.add(q2)

        val q3 = Question(
            id = 3,
            question = "What country does this flag belong to?",
            image = R.drawable.ic_flag_of_belgium,
            opt1 = "Argentina",
            opt2 = "Armenia",
            opt3 = "Australia",
            opt4 = "Belgium",
            correctAnswer = 4
        )
        questionList.add(q3)

        return questionList
    }
}