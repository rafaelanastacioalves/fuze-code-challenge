package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class Live(
    @SerializedName("opens_at") val opensAt: String? = null,
    @SerializedName("supported") val supported: Boolean = false,
    @SerializedName("url") val url: String? = null
)
