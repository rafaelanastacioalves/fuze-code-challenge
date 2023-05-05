package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("acronym")
    val acronym: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("modified_at")
    val modifiedAt: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("players")
    val players: List<Player>? = null
)