package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rafaelanastacioalves.fuzecodechallenge.R
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.League
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Opponent
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.OpponentDetails
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Player
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Serie
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Team
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
    Log.d("Compose", "Composing ScheduledAtRepresentation")

    Text(modifier = modifier, text = match.beginAt.orEmpty())
}

@Composable
fun LeagueAndSerieRepresentation(modifier: Modifier, match: Match) {
    Log.d("Compose", "Composing League and Serie Representation")

    val league = match.league.name
    val serie = match.serie.name
    Text(modifier = modifier, text = "$league + $serie")
}

@Composable
fun TeamRepresentation(modifier: Modifier, match: Match) {
    Log.d("Compose", "Composing Team Representation")
    Row(modifier = modifier.clipToBounds()) {
        if (match.opponents.isEmpty().not()) {
            Team1Area(modifier, details = match.opponents.getOrNull(0)?.opponentDetails)
            Text(modifier = modifier, text = "VS")
            Team2Area(modifier, details = match.opponents.getOrNull(1)?.opponentDetails)
        } else {
            Text(modifier = modifier, text = "Opponents not defined")
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Team2Area(modifier: Modifier, details: OpponentDetails?) {
    if (details != null) {
        CoreTeamImage(modifier = modifier, details = details, teamNumber = 2)
    } else {
        Text(modifier = modifier, text = "Opponent not defined")
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Team1Area(modifier: Modifier, details: OpponentDetails?) {
    if (details != null) {
        CoreTeamImage(modifier, details, teamNumber = 1)
    } else {
        Text(modifier = modifier, text = "Opponent not defined")
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun CoreTeamImage(
    modifier: Modifier,
    details: OpponentDetails,
    teamNumber: Int,
) {
    Column(modifier) {
        if (
            false
//            details.imageUrl.isNullOrEmpty().not()
        ) {
            GlideImage(model = details.imageUrl, contentDescription = details.slug)
        } else {
            Image(
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.mipmap.placeholder),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Text(modifier = modifier, text = "Time $teamNumber")
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
                imageUrl = ""
            )
        ),
        Opponent(
            OpponentDetails(
                imageUrl = ""
            )
        )
    )
)

val sampleMatchDetails = Match(
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
                imageUrl = ""
            )
        ),
        Opponent(
            OpponentDetails(
                imageUrl = ""
            )
        )
    ),
    teamList = listOf(
        Team(
            players = listOf(
                Player(
                    firstName = "Player N 1",
                    imageUrl = "",
                    name = "Nick Name 1"
                ),
                Player(
                    firstName = "Player N 2",
                    imageUrl = "",
                    name = "Nick Name 2"
                ),
                Player(
                    firstName = "Player N 3",
                    imageUrl = "",
                    name = "Nick Name 3"
                ),
                Player(
                    firstName = "Player N 4",
                    imageUrl = "",
                    name = "Nick Name 4"
                ),
                Player(
                    firstName = "Player N 5",
                    imageUrl = "",
                    name = "Nick Name 5"
                )
            )
        ),
        Team(
            players = listOf(
                Player(
                    firstName = "Player N 6",
                    imageUrl = "",
                    name = "Nick Name 6"
                ),
                Player(
                    firstName = "Player N 7",
                    imageUrl = "",
                    name = "Nick Name 7"
                ),
                Player(
                    firstName = "Player N 8",
                    imageUrl = "",
                    name = "Nick Name 8"
                ),
                Player(
                    firstName = "Player N 9",
                    imageUrl = "",
                    name = "Nick Name 9"
                ),
                Player(
                    firstName = "Player N 10",
                    imageUrl = "",
                    name = "Nick Name 10"
                )
            )
        )
    )
)