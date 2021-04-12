package com.e_quarium.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R

const val EXTRA_TEXT = "text to display"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val premierBouton = findViewById<Button>(R.id.buttonValider)
        val textView = findViewById<TextView>(R.id.txtView)
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val btnNext = findViewById<Button>(R.id.button2)



        premierBouton.setOnClickListener{
            var textSaisi = editText.text.toString()
            var comparaison = "new"
            if (textSaisi == comparaison) {
                val layoutPrincipal : ConstraintLayout = findViewById(R.id.LayoutPrincipal)
                val deuxTextView : TextView = TextView(this)
                deuxTextView.setText(textSaisi)
                layoutPrincipal.addView(deuxTextView)
            } else textView.setText(textSaisi)


        }

        btnNext.setOnClickListener{
            val test = "ca marche"
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra(EXTRA_TEXT, test )
            startActivity(intent)
        }


    }
}