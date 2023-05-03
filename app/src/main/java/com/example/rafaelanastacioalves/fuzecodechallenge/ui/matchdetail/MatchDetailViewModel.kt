import androidx.lifecycle.*
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor


class MatchDetailViewModel(
    val entityDetailsInteractor: Interactor<Resource<Match>, EntityDetailsInteractor.RequestValues>
) : ViewModel() {

    val _entityDetails = MutableLiveData<Resource<Match>>()
    val entityDetails: LiveData<Resource<Match>> = _entityDetails


    fun loadData(entityId: String?): LiveData<Resource<Match>> {
        _entityDetails.postValue(Resource.loading())
        return entityDetailsInteractor.execute(
            scope = viewModelScope,
            params =  entityId?.let {id ->
                EntityDetailsInteractor.RequestValues(id)
            }
        ).asLiveData()
    }

}

// make a factory class for EntityDetailsViewModel
@Suppress("UNCHECKED_CAST")
class MatchDetailViewModelFactory(val interactor: EntityDetailsInteractor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MatchDetailViewModel(interactor) as T
}




