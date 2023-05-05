import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Player
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.ScheduledAtRepresentation
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.TeamRepresentation
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.sampleMatchDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetail(viewModel: MainScreenViewModel, goBack: () -> Unit) {

    Log.d("MatchDetail", "Creating Screen Detail With Compose")
    val matchViewState by viewModel.matchDetails.observeAsState(ViewState.Loading)
    val selectedMatch = viewModel.selecteMatchLiveDAta.value!!
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "${selectedMatch.league.name} - ${selectedMatch.serie.name}")

                },
                navigationIcon = {
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

                is ViewState.Loading -> Text(modifier = Modifier.padding(padding), text = "Loading")
            }
        }
    }
}

@Composable
private fun MatchDetail(modifier: Modifier, match: Match) {
    Surface(modifier = Modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TeamRepresentation(modifier = modifier, match = match)
            ScheduledAtRepresentation(modifier = modifier, match = match)
            Row(modifier = modifier) {
                LazyColumn(modifier) {
                    items(match.teamList!![0].players!!){ item ->
                        PlayerItemInfo(modifier = modifier, item)

                    }
                }
                LazyColumn(modifier) {
                    items(match.teamList!![1].players!!) { item ->
                        PlayerItemInfo(modifier = modifier, item)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerItemInfo(modifier: Modifier, player: Player) {
    Row(modifier = modifier) {
        Column(modifier = Modifier) {
            Text(text = player.name.orEmpty())
            Text(text = player.firstName.orEmpty())
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

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun MatchDetailPreview() {
    MatchDetail(modifier = Modifier, match = sampleMatchDetails)
}


