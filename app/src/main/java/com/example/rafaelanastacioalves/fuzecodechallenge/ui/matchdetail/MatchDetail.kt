import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rafaelanastacioalves.fuzecodechallenge.R
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Player
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.ScheduledAtRepresentation
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.TeamRepresentation
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.sampleMatchDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

@Composable
fun MatchDetail(viewModel: MatchDetailViewModel) {

    Log.d("MatchDetail", "Creating Screen Detail With Compose")
    val entityDetailsResource by viewModel.matchDetails.observeAsState(ViewState.Loading)
    entityDetailsResource.let { viewState ->
        when(viewState) {
            is ViewState.Success -> viewState.viewDate.let {
                MatchDetail(
                    modifier = Modifier,
                    match = it
                )
            }

            is ViewState.Error -> Text(text = "Error: ${viewState.message}")
            is ViewState.Loading -> Text("Loading")
        }
    }
}

@Composable
private fun MatchDetail(modifier: Modifier, match: Match) {
    Surface(modifier = Modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TeamRepresentation(modifier = modifier, match = match)
            ScheduledAtRepresentation(modifier = modifier, match = match)
            Row(modifier = modifier){
                val listSize = match.players.size
                LazyColumn(modifier){
                    items(match.players.subList(0,(listSize/2) )) { item ->
                        PlayerItemInfo(modifier = modifier, item)

                    }
                }

                LazyColumn(modifier){
                    items(match.players.subList((listSize/2 ), listSize)) { item ->
                        PlayerItemInfo(modifier = modifier, item)
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerItemInfo(modifier: Modifier, player : Player) {
    Row(modifier = modifier) {
        Column(modifier = Modifier) {
            Text(text = player.name.orEmpty())
            Text(text = player.firstName.orEmpty())
        }
        Image(
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.mipmap.placeholder),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun MatchDetailPreview() {
    MatchDetail(modifier = Modifier, match = sampleMatchDetails)
}


