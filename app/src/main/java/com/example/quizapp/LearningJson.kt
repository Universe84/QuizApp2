package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import kotlin.math.abs

class LearningJson : AppCompatActivity() {
    companion object {
        val TAG = "LearningJson"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_learning_json)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gson = Gson()
        val inputStream = resources.openRawResource(R.raw.pluslife)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val test = gson.fromJson(jsonString, PlusLifeTest::class.java)
        Log.d(TAG, "onCreate: $test")

        val channelList = mutableListOf<Double>()
        val channelListDifferences = mutableListOf<Double>()
        val channelListDifferencesMax = mutableListOf<Double>()
        var maxValue: Double = Double.MIN_VALUE
        var minValue: Double = Double.MAX_VALUE
        var avgValue: Double = 0.0
        var largestDifference: Double = 0.0
        var largestDifferenceIndex: Int = 0
        var targetTemp: Double = test.targetTemp
        var numOfTempsThatWereOff: Int = 0
        var trueOrFalse : Boolean = true
        for (tempSample in test.testData.temperatureSamples) {

            if (maxValue < tempSample.temp) {
                maxValue = tempSample.temp
            }
            if (minValue > tempSample.temp) {
                minValue = tempSample.temp
            }
            if (abs(tempSample.temp - targetTemp) > 0.5) {
                numOfTempsThatWereOff++
            }
            avgValue += tempSample.temp
        }
        for (i in test.testData.temperatureSamples.indices) {
            if (abs(test.testData.temperatureSamples[i].temp - targetTemp) > largestDifference) {
                largestDifference = test.testData.temperatureSamples[i].temp - targetTemp
                largestDifferenceIndex = i
            }
        }

        for(sample in test.testData.samples){
            if(sample.startingChannel == 3.0){
                channelList.add(sample.firstChannelResult)
            }
        }
        for(i in 0 ..<(channelList.size - 1)){
            if(channelList[i] > channelList[i + 1]) {
                trueOrFalse = false
            }

            channelListDifferences.add(abs(channelList[i]-channelList[i+1]))
        }
        var largestMax : Int = Int.MIN_VALUE
        var secondMax : Int = Int.MIN_VALUE
        var thirdMax : Int = Int.MIN_VALUE
        for(i in channelListDifferences.indices){
            if(largestMax < channelListDifferences[i].toInt()){
                largestMax = channelListDifferences[i].toInt()
            }

        }
        for(i in channelListDifferences.indices){
            if(secondMax < channelListDifferences[i].toInt() && channelListDifferences[i].toInt() != largestMax){
                secondMax = channelListDifferences[i].toInt()
            }

        }
        for(i in channelListDifferences.indices){
            Log.d(TAG, "onCreate: currentThird: $thirdMax large: $largestMax  current: ${channelListDifferences[i]}" )
            if(thirdMax < channelListDifferences[i].toInt() && channelListDifferences[i].toInt() != largestMax && channelListDifferences[i].toInt() != secondMax){
                thirdMax = channelListDifferences[i].toInt()

            }

        }
        channelListDifferencesMax.add(largestMax.toDouble())
        channelListDifferencesMax.add(secondMax.toDouble())
        channelListDifferencesMax.add(thirdMax.toDouble())



        avgValue /= test.testData.temperatureSamples.size
        Log.d(
            TAG,
            "onCreate : $maxValue, $minValue, $avgValue, $largestDifference, $numOfTempsThatWereOff, $largestDifferenceIndex, $trueOrFalse, $channelListDifferencesMax"
        )

    }
}