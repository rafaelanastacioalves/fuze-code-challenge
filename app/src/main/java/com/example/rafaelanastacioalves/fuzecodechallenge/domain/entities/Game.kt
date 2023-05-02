package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id") val id: Int,
    @SerializedName("begin_at") val beginAt: String?,
    @SerializedName("complete") val complete: Boolean,
    @SerializedName("detailed_stats") val detailedStats: Boolean,
    @SerializedName("end_at") val endAt: String?,
    @SerializedName("finished") val finished: Boolean,
    @SerializedName("forfeit") val forfeit: Boolean,
    @SerializedName("length") val length: Int?,
    @SerializedName("match_id") val matchId: Int,
    @SerializedName("position") val position: Int,
    @SerializedName("status") val status: String,
    @SerializedName("winner") val winner: Winner?,
    @SerializedName("winner_type") val winnerType: String
)