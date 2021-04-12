package com.example.listview

import android.content.Context
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText as makeText1

class LivreAdapter(items: ArrayList<Livre>, ctx: Context) :
    ArrayAdapter<Livre>(ctx, R.layout.list_item_recipe, items) {

    //view holder est utilisé pour approuver les appels findViewById
    private class LivreItemViewHolder {
        internal var image: ImageView? = null
        internal var title: TextView? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view

        val viewHolder: LivreItemViewHolder
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_item_recipe, viewGroup, false)

            viewHolder = LivreItemViewHolder()
            viewHolder.title = view!!.findViewById<View>(R.id.Titre) as TextView
            viewHolder.image = view.findViewById<View>(R.id.image) as ImageView
        } else {
            viewHolder = view.tag as LivreItemViewHolder
        }

        val livre = getItem(i)
        viewHolder.title!!.text = livre!!.titre
        viewHolder.image!!.setImageResource(R.drawable.ic_launcher_background)

        viewHolder.image!!.setOnClickListener {
            Toast.makeText(context, "clicked image of " + livre!!.titre, Toast.LENGTH_SHORT).show()
        }

        view.tag = viewHolder

        return view
    }
}