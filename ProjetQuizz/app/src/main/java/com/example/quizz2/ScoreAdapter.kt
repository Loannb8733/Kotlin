package com.example.quizz2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ScoreAdapter(items: ArrayList<Score>, ctx : Context) :
        ArrayAdapter<Score>(ctx, R.layout.list_item_recipe,items) {

    private class ScoreItemViewHolder {
        internal var photo: ImageView? = null
        internal var position: TextView? = null
        internal var nom: TextView? = null
        internal var score: TextView? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view

        val viewHolder: ScoreItemViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_item_recipe, viewGroup, false)

            viewHolder = ScoreItemViewHolder()
            viewHolder.photo = view!!.findViewById<View>(R.id.photo) as ImageView
            viewHolder.nom = view!!.findViewById<View>(R.id.nom) as TextView
            viewHolder.score = view!!.findViewById<View>(R.id.score) as TextView
            viewHolder.position = view!!.findViewById<View>(R.id.position) as TextView
        } else {
            viewHolder = view.tag as ScoreItemViewHolder
        }

        val score = getItem(i)
        viewHolder.photo!!.setImageResource(score!!.photo)
        viewHolder.position!!.text = i.toString()
        viewHolder.nom!!.text = score!!.nom
        viewHolder.score!!.text = score!!.score.toString()

        view.tag = viewHolder

        return view
    }
}