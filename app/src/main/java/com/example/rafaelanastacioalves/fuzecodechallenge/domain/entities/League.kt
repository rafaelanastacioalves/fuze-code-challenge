package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("modified_at") val modifiedAt: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("slug") val slug: String? = null,
    @SerializedName("url") val url: String? = null
)
