
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.sampleMatch
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MatchListingInteractor(appRepository: AppRepository) :
        Interactor<Resource<List<Match>>, MatchListingInteractor.RequestValues>() {



    override suspend fun run(requestValues: RequestValues?, flowCollector: FlowCollector<Resource<List<Match>>>){
        var finalList: MutableList<Match> = ArrayList<Match>()
        var number = 0
        while (number<=5) {
            delay(2000)
            number++
            finalList.add(0,
                sampleMatch
            )
            flowCollector.emit(
                Resource.success(finalList)
            )

        }


    }


    class RequestValues : Interactor.RequestValues// in this case we don't need nothing for this use case
}
