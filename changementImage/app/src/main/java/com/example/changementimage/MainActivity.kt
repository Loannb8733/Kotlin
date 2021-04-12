package com.example.changementimage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.image)
        val btnPlay = findViewById<Button>(R.id.btnChange) //initialiser le bouton
        btnPlay.setOnClickListener { //lors de l appuie sur le btn
            val image2 = image.setImageResource(R.mipmap.ic_launcher_foreground1)
        }
    }
}