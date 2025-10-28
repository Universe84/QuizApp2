package com.example.quizapp

import android.content.Context
import android.content.res.Resources

class Quiz (private val questions : List<QuizQuestion>){
    var points = 0
    var currentQuestion = 0

//use the questions list
    fun areThereMoreQuestions() : Boolean{
        return currentQuestion < questions.size
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

    fun getGambler(context : Resources) : String{
        if(points < 500){
            return context.getString(R.string.clash_royale)
        }
        if(points < 750){
            return context.getString(R.string.poker)
        }
        if(points < 950){
            return context.getString(R.string.black_jack)
        }
        else{
            return context.getString(R.string.roulette)
        }

    }


}