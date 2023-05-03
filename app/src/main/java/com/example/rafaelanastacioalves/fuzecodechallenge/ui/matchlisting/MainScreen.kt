import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rafaelanastacioalves.fuzecodechallenge.R
import com.example.rafaelanastacioalves.fuzecodechallenge.application.MainApplication
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.MatchItemList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainScreenViewModel, onNavigate: (Int) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.matches))
                }
            )
        }
    ) {
        val viewState = viewModel.matchListLiveData.observeAsState()
        when(viewState.value) {
            is ViewState.Success -> {
                val observableListState = viewModel.observableListState.observeAsState()
                MatchListScreen(list = observableListState.value.orEmpty(), onNavigate = onNavigate)
            }
            is ViewState.Error, null -> {
                Surface(modifier = Modifier.padding(it)){
                    Text(text = stringResource(id = R.string.error))

                }
            }
            is ViewState.Loading -> {
                Surface(modifier = Modifier.padding(it)){
                    Text(text = stringResource(id = R.string.loading))

                }
            }

        }


    }
}

@Composable
fun MatchListScreen( modifier: Modifier = Modifier, list: List<Match>, onNavigate: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("list")
    ) {
        items(items = list) { match ->
            MatchItemList(match = match, modifier = Modifier.clickable {
                onNavigate(1)
            })
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val respository = MainApplication().appRepository
    val viewModel = MainScreenViewModel(MatchListingInteractor(respository))

    MainScreen(viewModel = viewModel, {})
}
