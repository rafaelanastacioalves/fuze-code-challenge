import android.annotation.SuppressLint
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.*
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


/**
 * Learned from the following links:
 * [Jetpack Compose State codelab](https://developer.android.com/codelabs/jetpack-compose-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-state#10)
 * and
 * [Flow Codelab](https://developer.android.com/codelabs/advanced-kotlin-coroutines#10)
 */


class MainScreenViewModel(
    val matchListingInteractor: Interactor<Resource<List<Match>>, MatchListingInteractor.RequestValues>,
    val matchDetailInteractor: MatchDetailInteractor,
) : ViewModel() {

    val _matchListLiveData: MutableLiveData<ViewState<List<Match>>> = loadMatchList().map {
        toViewState(it)
    } as MutableLiveData<ViewState<List<Match>>>

    val matchListLiveData = _matchListLiveData
    private fun toViewState(it: @JvmSuppressWildcards Resource<List<Match>>) =
        when (it.status) {
            Resource.Status.SUCCESS -> ViewState.Success(it.data!!)
            Resource.Status.INTERNAL_SERVER_ERROR -> ViewState.Error(it.message)
            Resource.Status.GENERIC_ERROR -> ViewState.Error(it.message)
            Resource.Status.LOADING -> ViewState.Loading
        }

    val observableListState = matchListLiveData.map { state ->
        when (state) {
            is ViewState.Success -> state.viewDate
            else -> emptyList<Match>()
        }.toMutableStateList()

    }

    private val _selecteMatchLivedata = MutableLiveData<Match?>(null)
    val selecteMatchLiveDAta: LiveData<Match?> = _selecteMatchLivedata

    fun loadMatchList(): LiveData<Resource<List<Match>>> {
        return matchListingInteractor.execute(viewModelScope, null).asLiveData()
    }

    fun refreshMatchList() {
        matchListingInteractor.execute(viewModelScope, null).map {
            _matchListLiveData.postValue(toViewState(it))
        }.launchIn(viewModelScope)

    }

    fun setSelectedMatch(match: Match) {
        _selecteMatchLivedata.postValue(match)
    }

    val matchDetails = loadMatchDetail().map {
        when (it.status) {
            Resource.Status.SUCCESS -> ViewState.Success(it.data!!)
            Resource.Status.INTERNAL_SERVER_ERROR -> ViewState.Error(it.message)
            Resource.Status.GENERIC_ERROR -> ViewState.Error(it.message)
            Resource.Status.LOADING -> ViewState.Loading
        }
    }


    fun loadMatchDetail(): LiveData<Resource<Match>> {
        return selecteMatchLiveDAta.switchMap {
            it?.let {
                matchDetailInteractor.execute(
                    scope = viewModelScope,
                    params = selecteMatchLiveDAta.value?.let {
                        MatchDetailInteractor.RequestValues(it)
                    }
                ).asLiveData()
            }
        }

    }
}

@Suppress("UNCHECKED_CAST")
class MainScreenViewModelFactory(
    private val matchListingInteractor: Interactor<Resource<List<Match>>, MatchListingInteractor.RequestValues>,
    private val interactor: MatchDetailInteractor,
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MainScreenViewModel(matchListingInteractor, interactor) as T)

}