package com.prapagorn.example.avengers.heroes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prapagorn.example.avengers.entities.Hero
import kotlinx.android.synthetic.main.vh_hero.view.*

class HeroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(hero: Hero, onClick: ((id: String
                                    , heroImageView: View
                                    , heroNameTextView: View) -> Unit)?) {
        itemView.apply {
            Glide.with(context)
                .load(hero.imgUrl)
                .into(ivHero)
            tvName.text = hero.name
            tvBio.text = hero.bioShort
            setOnClickListener {
                onClick?.invoke(hero.id, ivHero, tvName)
            }
        }
    }
}