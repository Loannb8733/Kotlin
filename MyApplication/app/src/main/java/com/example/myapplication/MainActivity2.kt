package com.e_quarium.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = intent
        var chaine : String? = intent.getStringExtra(EXTRA_TEXT)

        val textView = findViewById<TextView>(R.id.textView2)
        textView?.text=chaine
        textView.setText(chaine)

        val btnRetour = findViewById<Button>(R.id.buttonRetour)
        btnRetour.setOnClickListener {
            finish()
        }
    }
}