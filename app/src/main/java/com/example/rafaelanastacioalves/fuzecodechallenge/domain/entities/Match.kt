package com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

  data class Match(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("begin_at")
    val beginAt: String? = null,
    @SerializedName("end_at")
    val endAt: String? = null,
    @SerializedName("forfeit")
    val forfeit: Boolean = false,
    @SerializedName("match_type")
    val matchType: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("number_of_games")
    val numberOfGames: Int = 0,
    @SerializedName("game_advantage")
    val gameAdvantage: String? = null,
    @SerializedName("draw")
    val draw: Boolean = false,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("live")
    val live: Live = Live(),
    @SerializedName("opponents")
    val opponents: List<Opponent> = listOf(),
    @SerializedName("winner")
    val winner: Winner? = null,
    @SerializedName("games")
    val games: List<Game> = listOf(),
    @SerializedName("serie")
    val serie: Serie = Serie(),
    @SerializedName("league")
    val league: League = League(),
    @SerializedName("detailed_stats")
    val detailedStats: Boolean = false,
    @SerializedName("original_scheduled_at")
    val originalScheduledAt: String? = null,
    @SerializedName("rescheduled")
    val rescheduled: Boolean = false,
    @SerializedName("league_id")
    val leagueId: String? = null,
    @SerializedName("serie_id")
    val serieId: Int = 0,
    @SerializedName("results")
    val results: List<Result> = listOf(),
    @SerializedName("streams_list")
    val streamsList: List<Stream> = listOf(),
    var teamList: List<Team>? = null
)
