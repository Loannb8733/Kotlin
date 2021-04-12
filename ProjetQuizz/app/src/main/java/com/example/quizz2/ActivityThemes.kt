package com.example.quizz2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

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

        val playThemeFlag = findViewById<Button>(R.id.btnThemeFlag)
        playThemeFlag.setOnClickListener {
            val intent = Intent(this, ActivityQuestionsFlag::class.java)
            startActivity(intent)

        }

        val playThemeGeneralCulture = findViewById<Button>(R.id.btnThemeGeneralCulture)
        playThemeGeneralCulture.setOnClickListener {
            val intent = Intent(this, ActivityQuestionsGeneralCulture::class.java)
            startActivity(intent)
        }

        val playThemeFood = findViewById<Button>(R.id.btnThemeKitchen)
        playThemeFood.setOnClickListener {
            val intent = Intent(this, ActivityQuestionsKitchen::class.java)
            startActivity(intent)
        }

        val playThemeLogos = findViewById<Button>(R.id.btnThemeLogos)
        playThemeLogos.setOnClickListener {
            val intent = Intent(this, ActivityQuestionsLogos::class.java)
            startActivity(intent)
        }
    }
}