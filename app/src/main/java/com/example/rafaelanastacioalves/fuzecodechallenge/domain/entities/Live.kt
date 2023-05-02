package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class Live(
    @SerializedName("opens_at") val opensAt: String?,
    @SerializedName("supported") val supported: Boolean,
    @SerializedName("url") val url: String?
)