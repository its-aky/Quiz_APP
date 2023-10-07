package com.example.quizapp

object Constants {


    const val USER_NAME:String="username"

    const val TOTAL_QUESTIONS:String="total_questions"

    const val CORRECT_ANSWER:String="correct_answer"


    fun getQuestions():ArrayList<Question>{//creating an array list of Question data class
        val questionsList=ArrayList<Question>()

        val que1=Question(
            1,"Which Country's flag is this?",
            R.drawable.ic_flag_of_australia,//image also has an data type as Int
            "Argentina","Australia",
            "Germany","Austria",

            2
        )
        questionsList.add(que1)

        val que2=Question(
            2,"Which Country's flag is this?",
            R.drawable.ic_flag_of_argentina,
            "Argentina","Australia",
            "Turkey","India",

            1
        )
        questionsList.add(que2)

        val que3=Question(
            3,"Which Country's flag is this?",
            R.drawable.ic_flag_of_belgium,
            "Belgium","Australia",
            "Germany","Austria",

            1
        )
        questionsList.add(que3)

        val que4=Question(
            4,"Which Country's flag is this?",
            R.drawable.ic_flag_of_brazil,
            "Belgium","India",
            "Germany","Brazil",

            4
        )
        questionsList.add(que4)

        val que5=Question(
            5,"Which Country's flag is this?",
            R.drawable.ic_flag_of_fiji,
            "Iran","Fiji",
            "Guyana","Canada",

            2
        )
        questionsList.add(que5)

        val que6=Question(
            6,"Which Country's flag is this?",
            R.drawable.ic_flag_of_denmark,
            "Canada","Denmark",
            "France","Chile",

            2
        )
        questionsList.add(que6)

        val que7=Question(
            7,"Which Country's flag is this?",
            R.drawable.ic_flag_of_india,
            "Greece","Cambodia",
            "India","Chile",

            3
        )
        questionsList.add(que7)

        val que8=Question(
            8,"Which Country's flag is this?",
            R.drawable.ic_flag_of_new_zealand,
            "Hungary","Australia",
            "France","New Zealand",

            4
        )
        questionsList.add(que8)

        val que9=Question(
            9,"Which Country's flag is this?",
            R.drawable.ic_flag_of_germany,
            "Canada","Germany",
            "Kuwait","Nigeria",

            2
        )
        questionsList.add(que9)

        val que10=Question(
            10,"Which Country's flag is this?",
            R.drawable.ic_flag_of_kuwait,
            "South Africa","Spain",
            "Kuwait","Sweden",

            3
        )
        questionsList.add(que10)



        return questionsList


    }

}