package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("score") val score: Int,
    @SerializedName("team_id") val teamId: Int
)