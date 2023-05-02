package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Match(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("begin_at")
    val beginAt: String?,
    @SerializedName("end_at")
    val endAt: String?,
    @SerializedName("forfeit")
    val forfeit: Boolean,
    @SerializedName("match_type")
    val matchType: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("number_of_games")
    val numberOfGames: Int,
    @SerializedName("game_advantage")
    val gameAdvantage: String?,
    @SerializedName("draw")
    val draw: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("live")
    val live: Live,
    @SerializedName("opponents")
    val opponents: List<Opponent>,
    @SerializedName("winner")
    val winner: Winner?,
    @SerializedName("games")
    val games: List<Game>,
    @SerializedName("serie")
    val serie: Serie,
    @SerializedName("league")
    val league: League,
    @SerializedName("detailed_stats")
    val detailedStats: Boolean,
    @SerializedName("original_scheduled_at")
    val originalScheduledAt: String?,
    @SerializedName("rescheduled")
    val rescheduled: Boolean,
    @SerializedName("league_id")
    val leagueId: Int,
    @SerializedName("serie_id")
    val serieId: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("streams_list")
    val streamsList: List<Stream>
)