package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import com.google.gson.annotations.SerializedName


data class Player(
    @SerializedName("age")
    val age: Int? = null,
    @SerializedName("birthday")
    val birthday: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("modified_at")
    val modifiedAt: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nationality")
    val nationality: String? = null,
    @SerializedName("role")
    val role: Any? = null,
    @SerializedName("slug")
    val slug: String? = null
)