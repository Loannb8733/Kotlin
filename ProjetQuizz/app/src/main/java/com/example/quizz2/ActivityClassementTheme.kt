package com.example.quizz2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView

class ActivityClassementTheme : AppCompatActivity() {
    var themes:ArrayList<String> = arrayListOf(
        "Football",
        "Drapeaux",
        "Culture Général",
        "Cuisine",
        "Logos"
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classement_theme)

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


        val themeAdapter:ThemeAdapter =ThemeAdapter(themes, this)

        //Récupération de la ListView
        var lView = findViewById<ListView>(R.id.listViewTheme)

        //Affectation de l'adapter au ListView
        lView.setAdapter(themeAdapter)

        lView.setOnItemClickListener {
                parent,
                view,
                position,
                id ->
            val intent = Intent(this@ActivityClassementTheme, ActivityClassement::class.java)
            val texte = lView.getItemAtPosition(position).toString()
            intent.putExtra("Thème",texte)
            startActivity(intent)
        }
    }
}