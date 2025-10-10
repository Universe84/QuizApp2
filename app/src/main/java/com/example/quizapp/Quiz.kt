package com.example.quizapp

class Quiz (private val questions : List<QuizQuestion>){
    var points = 0
    var currentQuestion = 1
    var answerPicked = 0

    fun areThereMoreQuestions() : Boolean{
        return currentQuestion > questions.size
    }

    fun getCurrentQuestion() : Int{
        return currentQuestion
    }

    fun increaseCurrentQuestion() : Void{
        currentQuestion++
    }

    fun getPoints() : Int{
        return points
    }

    fun increasePoints() : Void{
        points += questions[currentQuestion].answers[answerPicked]
    }

    fun setAnswerPicked(whatWasPicked : Int){
        answerPicked = whatWasPicked
    }

    fun getAnswerPicked() : Int{
        return answerPicked
    }
}