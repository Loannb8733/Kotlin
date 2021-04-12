package com.example.quizz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ActivityThemes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_themes)

        val arrowBack = findViewById<ImageView>(R.id.flecheRetour)
        arrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val backHome = findViewById<ImageView>(R.id.retourAccueil)
        backHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val playThemeFoot = findViewById<Button>(R.id.btnThemeFoot)
        playThemeFoot.setOnClickListener {
            val intent = Intent(this, ActivityQuestionsFoot::class.java)
            startActivity(intent)
        }
    }
}