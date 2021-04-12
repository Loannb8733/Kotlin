package com.example.cycledevie2;

class Livre (var Titre : String, var Auteur : String, var NombrePages : Int)
    /*Création d'un classe Livre avec comme paramètres le titre, l'auteur et le nombre de pages */ {
    var random  = (1..12).shuffled().first()
        // variable donnant un nombre aléatoire entre 1 & 12

    override fun toString(): String { //création de la méhode
        return ("Titre : $Titre $random\nAuteur : $Auteur\nNombre de pages : $NombrePages")
            //retourne le titre, l'auteur et le nombre de page du livre
    }
}

