import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rafaelanastacioalves.fuzecodechallenge.R
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Player
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.AppBar
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.LoadingScreen
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.TeamRepresentation
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.sampleMatchDetails
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme
import com.example.rafaelanastacioalves.fuzecodechallenge.utils.Utils

@Composable
fun MatchDetail(viewModel: MainScreenViewModel, goBack: () -> Unit) {

    Log.d("MatchDetail", "Creating Screen Detail With Compose")
    val matchViewState by viewModel.matchDetails.observeAsState(ViewState.Loading)
    val selectedMatch = viewModel.selecteMatchLiveDAta.value!!
    Scaffold(
        topBar = {
            AppBar(
                textTitleComposable = {
                    Text(
                        text = "${selectedMatch.league.name} + ${selectedMatch.serie.name}",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                },
                noavigationUpIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        modifier = Modifier.clickable(onClick = goBack)
                    )
                }
            )
        }
    ) { padding ->
        matchViewState.let { viewState ->
            when (viewState) {
                is ViewState.Success -> viewState.viewDate.let {
                    MatchDetail(
                        modifier = Modifier.padding(padding),
                        match = it
                    )
                }

                is ViewState.Error -> Text(
                    modifier = Modifier.padding(padding),
                    text = "Error: ${viewState.message}"
                )

                is ViewState.Loading -> LoadingScreen()
            }
        }
    }
}

@Composable
private fun MatchDetail(modifier: Modifier, match: Match) {
    Surface(modifier = Modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TeamRepresentation(modifier = modifier.padding(horizontal = 16.dp), match = match)
            Text(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = Utils.formatDateTime(match.beginAt.orEmpty()),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 1
            )
            Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                LazyColumn(
                    modifier.weight(0.5f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(match.teamList!![0].players!!) { item ->
                        PlayerItemInfoLeft(modifier = modifier, item)

                    }
                }
                Spacer(modifier.width(12.dp))
                LazyColumn(
                    modifier.weight(0.5f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(match.teamList!![1].players!!) { item ->
                        PlayerItemInfoRight(modifier = modifier, item)
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerItemInfoRight(modifier: Modifier, player: Player) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
    ) {
        Row(
            modifier = modifier
        ) {
            Image(
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.mipmap.placeholder),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(modifier = modifier.padding(start = 16.dp)) {
                Text(
                    text = player.name.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
                Text(
                    text = player.firstName.orEmpty(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    color = Color.DarkGray
                )
            }
            //        player.imageUrl?.let {
            //            GlideImage(model = player.imageUrl, contentDescription = player.slug)
            //        } ?: Image(
            //            modifier = modifier
            //                .size(88.dp)
            //                .clip(CircleShape),
            //            painter = painterResource(id = R.mipmap.placeholder),
            //            contentScale = ContentScale.Crop,
            //            contentDescription = null
            //        )
        }
    }
}

@Composable
fun PlayerItemInfoLeft(modifier: Modifier = Modifier, player: Player) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 12.dp, bottom = 8.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)

    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                modifier = modifier.padding(end = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = player.name.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Right,
                )
                Text(
                    text = player.firstName.orEmpty(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Right,
                    color = Color.DarkGray
                )
            }
            //        player.imageUrl?.let {
            //            GlideImage(model = player.imageUrl, contentDescription = player.slug)
            //        } ?:
            Image(
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.mipmap.placeholder),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

        }
    }
}

@Preview
@Composable
fun MatchDetailPreview() {
    RafaelanastacioalvesfuzechallengeTheme(darkTheme = true, dynamicColor = false) {

        MatchDetail(modifier = Modifier, match = sampleMatchDetails)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun PlayerItemInfoLeft() {
    RafaelanastacioalvesfuzechallengeTheme(darkTheme = true, dynamicColor = false) {
        val samplePlayer = sampleMatchDetails.teamList?.get(0)?.players?.get(0)
        PlayerItemInfoLeft(modifier = Modifier, player = samplePlayer!!)
    }
}


