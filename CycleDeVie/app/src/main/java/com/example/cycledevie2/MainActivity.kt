package com.example.cycledevie2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity(){

    val livre = Livre("livre", "max", 32).toString()
        //On creer un livre en appelant la méthode toString de la classe Livre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show() //creation du Toast
        val btnSvt = findViewById<Button>(R.id.btnSvt) //initialiser le bouton
        btnSvt.setOnClickListener { //lors de l appuie sur le btn
            val intent = Intent(this, MainActivity2::class.java) //creer un intent pour changer d'activité
            startActivity(intent) //lancer l'activité
        }

        var affLivre = findViewById<TextView>(R.id.aff) //initialisation du textView
        affLivre.setText(livre) //donne la valeur livre créé prcédemment au textView
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show() //creation du Toast

        val btnSvt = findViewById<Button>(R.id.btnSvt) //initialiser le bouton
        btnSvt.setOnClickListener { //lors de l appuie sur le btn
            val intent = Intent(this, MainActivity2::class.java) //creer un intent pour changer d'activité
            startActivity(intent) //lancer l'activité
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
        val btnSvt = findViewById<Button>(R.id.btnSvt)
        btnSvt.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show()
        //creation du Toast

        // On Sauvegarde les paramètres
        val editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit()
        editor.putString("son", livre)
        editor.apply()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show()
        //creation du Toast

        //Recuperer les paramètres sauvegarde précédemment
        val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val livreb = prefs.getString("son", "actif")
        var aff = findViewById<TextView>(R.id.aff) //initialisation du textView
        aff.setText(livreb + '\n' + '\n' + livre) //donne au setText les 2 derniers livres ajoutés
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }
}