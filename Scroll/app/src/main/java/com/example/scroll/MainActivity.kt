package com.example.scroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // layout_width : occupe la largeur complete de l'ecran -->
        // layout_height : occupe la largeur complete de l'ecran -->
        // layout_height : occupe la largeur complete de l'ecran -->
        /* fillViewport="true" : scrollView s'affiche en mode plein ecran,
            meme si les elements sont plus petits que la taille de l ecran*/
        // orientation="vertical" : aligne les elements verticalement en prenant toute la largeur -->

        var textViewString = findViewById<TextView>(R.id.HelloWorld) //initialisation du textView
        var chaine = "Contrary to popular belief, Lorem Ipsum is not simply random text. " +
                            "It has roots in a piece of classical Latin literature from 45 BC, " +
                            "making it over 2000 years old. Richard McClintock, a Latin professor at " +
                            "Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, " +
                            "consectetur, from a Lorem Ipsum passage, and going through the cites of the word in " +
                            "classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 " +
                            "and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, " +
                            "written in 45 BC. This book is a treatise on the theory of ethics, very popular during the " +
                            "Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line " +
                            "in section 1.10.32." //création d'une chaine de caractère

        textViewString.setText(chaine) //récupération tu textView auquel on passe la variable chaine
    }
}