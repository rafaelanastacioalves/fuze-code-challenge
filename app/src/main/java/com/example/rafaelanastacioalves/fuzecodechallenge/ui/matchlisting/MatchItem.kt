package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Dimension
import coil.size.Size
import com.example.rafaelanastacioalves.fuzecodechallenge.R
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.League
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Opponent
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.OpponentDetails
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Player
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Serie
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Team
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.Grey
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.Red
import com.example.rafaelanastacioalves.fuzecodechallenge.utils.Utils

@Composable
fun MatchItemList(modifier: Modifier = Modifier, match: Match) {
    Card(
        modifier = modifier.padding(24.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Column(modifier) {
            ScheduledAtRepresentation(modifier, match = match)
            TeamRepresentation(modifier = modifier, match)
            Divider(color = MaterialTheme.colors.onPrimary)
            LeagueAndSerieRepresentation(modifier = modifier, match = match)
        }
    }
}

@Composable
fun ScheduledAtRepresentation(modifier: Modifier, match: Match) {
    Log.d("Compose", "Composing ScheduledAtRepresentation")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        val backGroundColor = if(match.status == "running") Red else Grey
        val scheduledText = if(match.status == "running") stringResource(id = R.string.now) else Utils.formatDateTime(match.beginAt.orEmpty())
        Surface(
            modifier = modifier, shape = RoundedCornerShape(bottomStart = 16.dp), color = backGroundColor
        ) {
            Text(
                modifier = modifier.padding(8.dp),
                text = scheduledText ,
                textAlign = TextAlign.Center,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

@Composable
fun LeagueAndSerieRepresentation(modifier: Modifier, match: Match) {
    Log.d("Compose", "Composing League and Serie Representation")

    val league = match.league.name
    val serie = match.serie.name
    Row(modifier = modifier.padding(start = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = modifier.size(16.dp),
            contentScale = ContentScale.Fit,
            model = ImageRequest.Builder(LocalContext.current)
                .data(match.league.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = match.league.slug,
            placeholder = painterResource(id = R.drawable.team_logo),
            error = painterResource(id = R.drawable.team_logo)
        )
        Text(
            modifier = modifier.padding(start = 8.dp, bottom = 12.dp, top = 12.dp),
            text = "$league + $serie",
            fontSize = 8.sp
        )
    }
}

@Composable
fun TeamRepresentation(modifier: Modifier, match: Match) {
    Log.d("Compose", "Composing Team Representation")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center
    ) {
        if (match.opponents.isEmpty().not()) {
            Team1Area(modifier, details = match.opponents.getOrNull(0)?.opponentDetails)
            Column(modifier = modifier
                .fillMaxHeight()
                .padding(horizontal = 20.dp), verticalArrangement = Arrangement.Center) {
                Text(modifier = modifier.alpha(0.5f), text = "VS", fontSize = 12.sp)
            }
            Team2Area(modifier, details = match.opponents.getOrNull(1)?.opponentDetails)
        } else {
            Text(modifier = modifier, text = "Opponents not defined", fontSize = 8.sp)
        }
    }
}

@Composable
private fun Team2Area(modifier: Modifier, details: OpponentDetails?) {
        CoreTeamArea(modifier = modifier, details = details, teamNumber = 2)


}

@Composable
private fun Team1Area(modifier: Modifier, details: OpponentDetails?) {
            CoreTeamArea(modifier, details, teamNumber = 1)

}

@Composable
private fun CoreTeamArea(
    modifier: Modifier,
    details: OpponentDetails?,
    teamNumber: Int,
) {
    Column(modifier.padding(bottom = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = modifier.size(60.dp),
            contentScale = ContentScale.Fit,
            model = details?.imageUrl,
            contentDescription = details?.slug,
            placeholder = painterResource(id = R.drawable.team_logo),
            error = painterResource(id = R.drawable.team_logo)
        )
        Text(
            modifier = modifier,
            text = details?.name ?: "XXX",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onPrimary,
            maxLines = 1
        )
    }
}

// create a compose preview for MainEntityListItem
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun MatchItemListPreview() {
    RafaelanastacioalvesfuzechallengeTheme(darkTheme = false, dynamicColor = false) {
        MatchItemList(match = sampleMatch)
    }
}

val sampleMatch = Match(
    id = 1,
    beginAt = "2023-03-01T13:00:00Z",
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
    beginAt = "2023-03-01T13:00:00Z",
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