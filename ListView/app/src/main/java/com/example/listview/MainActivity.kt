package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    //creation de livre
    var livre1 = Livre("Comment etre beau", "https://diaryfrenchpua-60af.kxcdn.com/wp-content/uploads/2018/02/comment-devenir-plus-beau-930x620.jpg")
    var livre2 = Livre("Comment etre beau2","https://diaryfrenchpua-60af.kxcdn.com/wp-content/uploads/2018/02/comment-devenir-plus-beau-930x620.jpg")
    var livre3 = Livre("Comment etre moche", "https://www.helloworkplace.fr/wp-content/uploads/2018/06/iStock-beau.jpg")
    var livre4 = Livre("Comment etre moche2","https://www.helloworkplace.fr/wp-content/uploads/2018/06/iStock-beau.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Ajout des livres dans l'array List
        var arrayListLivre = ArrayList<Livre>()
        arrayListLivre.add(0, livre1)
        arrayListLivre.add(1, livre2)
        arrayListLivre.add(2, livre3)
        arrayListLivre.add(3, livre4)

        //création d'un tableau
        val tableau = arrayOf("Loann", "Guillaume", "Léo", "Florian", "Maël")
        var liste = findViewById<ListView>(R.id.ListView) //initialisation du ListView

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                //activité courante
                this,
                //layout des items de la liste
                android.R.layout.simple_list_item_1,
                //layout pour le textView des éléments de la liste
                android.R.id.text1,
                //tableau comportant les items
                tableau
        )

        //creation d'un adaptateur
        val adapterLivre: LivreAdapter = LivreAdapter (
            arrayListLivre, //appel de l'array list
        this
        )

        //affichage de la liste
        liste.adapter = adapter

        //affiche la liste de livres
        liste.adapter = adapterLivre

        liste.setOnItemClickListener {
            parent,
                    view,
                    position,
                    id ->
            val element = parent.getItemAtPosition(position).toString() //Item cliqué

            //Affiche un Toast dont le text est l'élément cliqué
            Toast.makeText(this, "Url image : $element", Toast.LENGTH_SHORT).show()
        }
    }
}