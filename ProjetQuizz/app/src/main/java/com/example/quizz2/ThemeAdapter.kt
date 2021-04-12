package com.example.quizz2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ThemeAdapter(items: ArrayList<String>, ctx : Context) :
        ArrayAdapter<String>(ctx, R.layout.list_item_recipe_theme,items) {

    private class ThemeItemViewHolder {
        internal var nomTheme: TextView? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view

        val viewHolder: ThemeItemViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_item_recipe_theme, viewGroup, false)

            viewHolder = ThemeItemViewHolder()
            viewHolder.nomTheme = view!!.findViewById<View>(R.id.nomTheme) as TextView
        } else {
            viewHolder = view.tag as ThemeItemViewHolder
        }

        val score = getItem(i)
        viewHolder.nomTheme!!.text = score!!

        view.tag = viewHolder

        return view
    }
}