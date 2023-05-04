import androidx.lifecycle.*
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.ViewState
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor


class MatchDetailViewModel(
    val matchDetailInteractor: Interactor<Resource<Match>, MatchDetailInteractor.RequestValues>,
    val matchId: String
) : ViewModel() {

    val matchDetails = loadData().map {
        when(it.status){
            Resource.Status.SUCCESS -> ViewState.Success(it.data!!)
            Resource.Status.INTERNAL_SERVER_ERROR -> ViewState.Error(it.message)
            Resource.Status.GENERIC_ERROR -> ViewState.Error(it.message)
            Resource.Status.LOADING -> ViewState.Loading
        }
    }


    fun loadData(): LiveData<Resource<Match>> {
        return matchDetailInteractor.execute(
            scope = viewModelScope,
            params =  MatchDetailInteractor.RequestValues(matchId)
        ).asLiveData()
    }

}

// make a factory class for EntityDetailsViewModel
@Suppress("UNCHECKED_CAST")
class MatchDetailViewModelFactory(val interactor: MatchDetailInteractor, val  matchId: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MatchDetailViewModel(interactor, matchId) as T
}




