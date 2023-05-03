package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class OpponentDetails(
    @SerializedName("acronym") val acronym: String? = null,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("location") val location: String? = null,
    @SerializedName("modified_at") val modifiedAt: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("slug") val slug: String? = null
)
