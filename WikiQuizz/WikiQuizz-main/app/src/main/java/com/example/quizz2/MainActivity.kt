package com.example.quizz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay = findViewById<Button>(R.id.btnPlay) //initialiser le bouton
        btnPlay.setOnClickListener { //lors de l appuie sur le btn
            val intent = Intent(this, ActivityThemes::class.java) //creer un intent pour changer d'activité
            startActivity(intent) //lancer l'activité
        }

        val btnRanking = findViewById<Button>(R.id.btnRanking) //initialiser le bouton
        btnRanking.setOnClickListener { //lors de l appuie sur le btn
            val intent = Intent(this, ActivityClassement::class.java) //creer un intent pour changer d'activité
            startActivity(intent) //lancer l'activité
        }

        val btnOpt = findViewById<Button>(R.id.btnOption) //initialiser le bouton
        btnOpt.setOnClickListener { //lors de l appuie sur le btn
            val intent = Intent(this, ActivityOptions::class.java) //creer un intent pour changer d'activité
            startActivity(intent) //lancer l'activité
        }
    }
}