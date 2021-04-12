package com.example.quizz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class ActivityClassement : AppCompatActivity() {
    var scores:ArrayList<Score> = arrayListOf(
            Score(R.drawable.logoapp, "Kévin", 100),
            Score(R.drawable.logoapp, "Loann", 50),
            Score(R.drawable.logoapp, "Guillaume", 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classement)

        val arrowBack = findViewById<ImageView>(R.id.flecheRetourRanking)
        arrowBack.setOnClickListener {
            finish()
        }

        val backHome = findViewById<ImageView>(R.id.retourAccueilRanking)
        backHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val intent = intent
        val textView2 = findViewById<TextView>(R.id.theme)

        textView2.text = intent.getStringExtra("Thème")

        val lView = findViewById<ListView>(R.id.listView)
        val scoreAdapter = ScoreAdapter(scores, this)
        lView.adapter = scoreAdapter
    }
}