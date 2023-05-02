package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class Stream(
    @SerializedName("embed_url") val embedUrl: String?,
    @SerializedName("language") val language: String,
    @SerializedName("main") val main: Boolean,
    @SerializedName("official") val official: Boolean,
    @SerializedName("raw_url") val rawUrl: String?
)