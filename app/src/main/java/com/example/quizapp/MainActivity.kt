package com.example.quizapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    private lateinit var currentQuestion : TextView
    private lateinit var topLeft : Button
    private lateinit var topRight : Button
    private lateinit var bottomLeft : Button
    private lateinit var bottomRight: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.quiz)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val type = object : TypeToken<List<QuizQuestion>>() { }.type
        val questions = gson.fromJson<List<QuizQuestion>>(jsonString, type)
        Log.d(TAG, "onCreate: $questions")

        val quizApp = Quiz(questions)


        currentQuestion = findViewById(R.id.textView_main_question)
        topLeft = findViewById(R.id.button_main_topLeft)
        topRight = findViewById(R.id.button_main_topRight)
        bottomLeft = findViewById(R.id.button_main_bottomLeft)
        bottomRight = findViewById(R.id.button_main_bottomRight)

        currentQuestion.text = questions.[quizApp.currentQuestion]
    }


}