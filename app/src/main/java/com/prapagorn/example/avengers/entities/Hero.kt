package com.prapagorn.example.avengers.entities

import com.google.firebase.database.PropertyName

data class Hero(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",

    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = "",

    @get:PropertyName("short_bio")
    @set:PropertyName("short_bio")
    var bioShort: String = "",

    @get:PropertyName("bio")
    @set:PropertyName("bio")
    var bio: String = "",

    @get:PropertyName("img_url")
    @set:PropertyName("img_url")
    var imgUrl: String = ""
)