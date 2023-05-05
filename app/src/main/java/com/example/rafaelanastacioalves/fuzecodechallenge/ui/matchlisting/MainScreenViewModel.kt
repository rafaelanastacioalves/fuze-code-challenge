import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.*
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor


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
    val matchListLiveData = loadDataIfNecessary().map {
        when (it.status) {
            Resource.Status.SUCCESS -> ViewState.Success(it.data!!)
            Resource.Status.INTERNAL_SERVER_ERROR -> ViewState.Error(it.message)
            Resource.Status.GENERIC_ERROR -> ViewState.Error(it.message)
            Resource.Status.LOADING -> ViewState.Loading
        }

    }

    val observableListState = matchListLiveData.map { state ->
        when (state) {
            is ViewState.Success -> state.viewDate
            else -> emptyList<Match>()
        }.toMutableStateList()

    }

    private val _selecteMatchLivedata = MutableLiveData<Match?>(null)
    val selecteMatchLiveDAta: LiveData<Match?> = _selecteMatchLivedata

    fun loadDataIfNecessary(): LiveData<Resource<List<Match>>> {
        return matchListingInteractor.execute(viewModelScope, null).asLiveData()
    }

    fun setSelectedMatch(match: Match) {
        _selecteMatchLivedata.postValue(match)
    }

    val matchDetails = loadData().map {
        when (it.status) {
            Resource.Status.SUCCESS -> ViewState.Success(it.data!!)
            Resource.Status.INTERNAL_SERVER_ERROR -> ViewState.Error(it.message)
            Resource.Status.GENERIC_ERROR -> ViewState.Error(it.message)
            Resource.Status.LOADING -> ViewState.Loading
        }
    }


    fun loadData(): LiveData<Resource<Match>> {
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