import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.rafaelanastacioalves.fuzecodechallenge.R
import com.example.rafaelanastacioalves.fuzecodechallenge.application.MainApplication
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.AppBar
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.LoadingScreen
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.MatchItemList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun MainScreen(viewModel: MainScreenViewModel, onNavigate: (Match) -> Unit) {

    Scaffold(
        topBar = {
            AppBar() {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.matches),
                    fontSize = 32.sp,
                )
            }
        }
    ) {
        val viewState = viewModel.matchListLiveData.observeAsState()
        when(viewState.value) {
            is ViewState.Success -> {
                val observableListState = viewModel.observableListState.observeAsState()
                if(observableListState.value.isNullOrEmpty().not()) {
                    MatchListScreen(list = observableListState.value.orEmpty(), onNavigate = onNavigate, onRefresh = {viewModel.refreshMatchList()})
                }
            }
            is ViewState.Error, null -> {
                Surface(modifier = Modifier.padding(it)){
                    Text(text = stringResource(id = R.string.error))

                }
            }
            is ViewState.Loading -> {
                LoadingScreen()
            }

        }


    }
}

@Composable
fun MatchListScreen( modifier: Modifier = Modifier, list: List<Match>, onNavigate: (Match) -> Unit, onRefresh: () -> Unit) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = false),
        onRefresh = onRefresh
    ) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colors.primary,
            contentColor = contentColorFor(MaterialTheme.colors.onPrimary)
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .testTag("list")
            ) {
                items(items = list) { match ->
                    MatchItemList(match = match, modifier = modifier.clickable {
                        onNavigate(match)
                    })
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val respository = MainApplication().appRepository
    val viewModel = MainScreenViewModel(MatchListingInteractor(respository), MatchDetailInteractor(respository))

    MainScreen(viewModel = viewModel, {})
}
