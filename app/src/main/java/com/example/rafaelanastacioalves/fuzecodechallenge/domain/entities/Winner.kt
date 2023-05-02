package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName


data class Winner(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("type")
    val type: String
)
