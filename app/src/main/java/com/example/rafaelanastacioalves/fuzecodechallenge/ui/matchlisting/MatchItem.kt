package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.League
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Opponent
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.OpponentDetails
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Serie
import com.example.rafaelanastacioalves.fuzecodechallenge.utils.Utils

@Composable
fun MatchItemList(modifier: Modifier = Modifier, match: Match) {
    Surface(modifier = modifier) {
        Column(modifier) {
            ScheduledAtRepresentation(modifier, match = match)
            TeamRepresentation(modifier = modifier, match)
            Divider()
            LeagueAndSerieRepresentation(modifier = modifier, match = match)
        }
    }
}

@Composable
fun ScheduledAtRepresentation(modifier: Modifier, match: Match) {
    Text(modifier = modifier, text = match.beginAt.orEmpty())
}

@Composable
fun LeagueAndSerieRepresentation(modifier: Modifier, match: Match) {
    val league = match.league.name
    val serie = match.serie.name
    Text(modifier = modifier, text = "$league + $serie")
}

@Composable
fun TeamRepresentation(modifier: Modifier, match: Match) {
    Row(modifier = modifier.clipToBounds()) {
        if (match.opponents.isEmpty().not()) {
            Time1(modifier, details = match.opponents.getOrNull(0)?.opponentDetails)
            Text(modifier = modifier, text = "VS")
            Time2(modifier, details = match.opponents.getOrNull(1)?.opponentDetails)
        } else {
            Text(modifier = modifier, text = "Opponents not defined")
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Time2(modifier: Modifier, details: OpponentDetails?) {
    if (details != null) {
        Column(modifier) {
            GlideImage(model = details.imageUrl, contentDescription = details.slug)
            Text(modifier = modifier, text = "Time 2")
        }
    } else {
        Text(modifier = modifier, text = "Opponent not defined")
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Time1(modifier: Modifier, details: OpponentDetails?) {
    if (details != null) {
        Column(modifier) {
            GlideImage(model = details.imageUrl, contentDescription = details.slug)
            Text(modifier = modifier, text = "Time 1")
        }
    } else {
        Text(modifier = modifier, text = "Opponent not defined")
    }
}

// create a compose preview for MainEntityListItem
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun MatchItemListPreview() {
    MatchItemList(match = sampleMatch)
}

val sampleMatch = Match(
    id = 1,
    beginAt = Utils.formatDateTime("2023-03-01T13:00:00Z"),
    name = "Match Name",
    league = League(
        name = "League Name"
    ),
    serie = Serie(
        name = "Serie Name"
    ),
    opponents = listOf(
        Opponent(
            OpponentDetails(
                imageUrl = "https://cdn.pandascore.co/images/team/image/126408/d8_wxn_rx.png"
            )
        ),
        Opponent(
            OpponentDetails(
                imageUrl = "https://cdn.pandascore.co/images/team/image/126208/illuminar.png"
            )
        )
    )
)