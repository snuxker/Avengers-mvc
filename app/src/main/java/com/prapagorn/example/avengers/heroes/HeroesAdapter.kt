package com.prapagorn.example.avengers.heroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prapagorn.example.avengers.R
import com.prapagorn.example.avengers.entities.Hero

class HeroesAdapter: RecyclerView.Adapter<HeroViewHolder>() {

    var heroList = listOf<Hero>()
    var onClick: ((id: String,
                   heroImageView: View,
                   heroNameTextView: View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeroViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_hero, parent, false)
        )

    override fun getItemCount() = heroList.size

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroList[position], onClick)
    }
}