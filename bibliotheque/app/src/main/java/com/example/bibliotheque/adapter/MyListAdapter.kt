package com.example.bibliotheque.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bibliotheque.R

class MyListAdapter(private val context: Activity, private val id: Array<String>, private val isbn: Array<String>, private val titre: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, isbn) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val isbnText = rowView.findViewById(R.id.textViewIsbn) as TextView
        val titreText = rowView.findViewById(R.id.textViewTitre) as TextView

        idText.text = "Id: ${id[position]}"
        isbnText.text = "Isbn: ${isbn[position]}"
        titreText.text = "Titre: ${titre[position]}"
        return rowView
    }
}