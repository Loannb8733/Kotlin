package com.example.quizz2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActivityQuestionsFoot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_foot)

        val textView = findViewById<TextView>(R.id.scoreQuestionsFoot);
        val number = 0
        textView.text = number.toString();
    }



    fun incrementPoint() {

    }


}