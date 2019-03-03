package com.prapagorn.example.avengers.herobio

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.prapagorn.example.avengers.R
import com.prapagorn.example.avengers.entities.Hero
import kotlinx.android.synthetic.main.activity_hero_bio.*

class HeroBioActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    companion object {
        const val EXTRA_HERO_ID = "HERO_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_bio)
        val heroId = intent.getStringExtra(EXTRA_HERO_ID)
        getHero(heroId)
    }

    private fun getHero(heroId: String) {
        database = FirebaseDatabase.getInstance().reference.child("data").child(heroId)
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HeroBioActivity, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.getValue<Hero>(Hero::class.java)?.bind()
            }
        })
    }

    private fun Hero.bind() {
        Glide.with(this@HeroBioActivity)
            .load(this.imgUrl)
            .into(ivHero)
        tvName.text = this.name
        tvBioShort.text = this.bioShort
        tvBio.text = this.bio
    }
}