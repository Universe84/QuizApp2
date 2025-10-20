package com.example.quizapp

class Quiz (private val questions : List<QuizQuestion>){
    var points = 0
    var currentQuestion = 0

//use the questions list
    fun areThereMoreQuestions() : Boolean{
        return currentQuestion > questions.size
    }

    fun getCurrentQuestion() : String{
        return questions[currentQuestion].question
    }

    fun increaseCurrentQuestion(){
        currentQuestion++
    }

    fun increasePoints(orange : Int){
        points += questions[currentQuestion].answers[orange]

    }

    fun getCurrentAnswer(orange : Int) : String{
        return questions[currentQuestion].choices[orange]
    }


}