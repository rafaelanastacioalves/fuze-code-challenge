
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import kotlinx.coroutines.flow.*

class MatchListingInteractor(val appRepository: AppRepository) :
        Interactor<Resource<List<Match>>, MatchListingInteractor.RequestValues>() {



    override suspend fun run(requestValues: RequestValues?, flowCollector: FlowCollector<Resource<List<Match>>>){
        var finalList: MutableList<Match> = ArrayList<Match>()
        var number = 0

        finalList.clear()
        val resource = appRepository.getMatchList()
            flowCollector.emit(resource)
    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
