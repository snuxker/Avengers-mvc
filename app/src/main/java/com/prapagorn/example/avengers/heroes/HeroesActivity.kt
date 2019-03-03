package com.prapagorn.example.avengers.heroes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.prapagorn.example.avengers.R
import com.prapagorn.example.avengers.entities.Hero
import com.prapagorn.example.avengers.herobio.HeroBioActivity
import kotlinx.android.synthetic.main.activity_hero.*



class HeroesActivity : AppCompatActivity() {

    private lateinit var heroesAdapter: HeroesAdapter
    private val database: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference.child("data")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        heroesAdapter = HeroesAdapter().apply {
            onClick = { heroId, heroImageView, heroNameTextView ->
                startHeroBioActivity(heroId, heroImageView, heroNameTextView)
            }
        }
        rvHeroes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = heroesAdapter
        }
        getHeroList()
    }

    private fun getHeroList() {
        database.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HeroesActivity, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val heroList = mutableListOf<Hero>()
                dataSnapshot.children.mapNotNullTo(heroList) {
                    it.getValue<Hero>(Hero::class.java)
                }
                heroesAdapter.let {
                    it.heroList = heroList
                    checkLoadingIndicator()
                    it.notifyDataSetChanged()
                }
            }
        })
    }

    private fun checkLoadingIndicator(){
        pbLoading.visibility = if (heroesAdapter.heroList.isNotEmpty()) {
            View.GONE
        }else {
            View.VISIBLE
        }
    }

    private fun startHeroBioActivity(heroId: String, heroImageView: View, heroNameTextView: View) {
        val intent = Intent(this, HeroBioActivity::class.java).apply {
            putExtra(HeroBioActivity.EXTRA_HERO_ID, heroId)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val heroImagePair = Pair(heroImageView, getString(R.string.transition_hero_image))
            val heroNamePair = Pair(heroNameTextView, getString(R.string.transition_hero_name))
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                heroImagePair, heroNamePair)
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }
}
