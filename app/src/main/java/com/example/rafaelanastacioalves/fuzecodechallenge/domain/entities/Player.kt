package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class Player(
    val assists: Int = 0,
    val deaths: Int = 0,
    @SerializedName("first_name") val firstName: String? = null,
    val headshots: Int = 0,
    @SerializedName("image_url") val imageUrl: String? = null,
    val kills: Int = 0,
    @SerializedName("last_name") val lastName: String? = null,
    val name: String? = null,
    val nationality: String? = null,
    @SerializedName("number_of_games") val numberOfGames: Int = 0,
    @SerializedName("player_id") val playerId: Int = 0,
    val slug: String? = null
)