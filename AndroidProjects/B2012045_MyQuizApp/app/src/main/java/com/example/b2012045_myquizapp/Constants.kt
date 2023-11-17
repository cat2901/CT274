package com.example.b2012045_myquizapp

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val questionOne = Question(
            1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Australia", "Armenia", "Austria",
            0,
        )
        questionsList.add(questionOne)

        val questionTwo = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Angola",
            "Austria",
            "Australia",
            "Armenia",
            2
        )
        questionsList.add(questionTwo)

        val questionThree = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Belarus",
            "Belize",
            "Brunei",
            "Brazil",
            3
        )
        questionsList.add(questionThree)



        return questionsList
    }
}