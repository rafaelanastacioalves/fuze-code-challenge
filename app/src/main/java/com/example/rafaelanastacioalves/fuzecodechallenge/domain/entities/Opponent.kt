package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class Opponent(
    @SerializedName("opponent") val opponent: OpponentDetails,
    @SerializedName("type") val type: String
)