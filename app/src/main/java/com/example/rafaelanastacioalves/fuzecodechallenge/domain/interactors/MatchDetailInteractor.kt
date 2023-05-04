import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.sampleMatchDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import kotlinx.coroutines.flow.FlowCollector

class MatchDetailInteractor(
    private val appRepository: AppRepository,
) : Interactor<Resource<Match>, MatchDetailInteractor.RequestValues>() {

    class RequestValues(val requestId: String) : Interactor.RequestValues

    override suspend fun run(
        requestValue: RequestValues?,
        flowCollector: FlowCollector<Resource<Match>>,
    ) {

        flowCollector.emit(Resource.loading())

        val resource : Resource<Match> = requestValue?.requestId?.let {
            appRepository.matchDetails(it)
        } ?: Resource(
            Resource.Status.GENERIC_ERROR, data = null, "No parameter sent to API"
        )
        flowCollector.emit(resource)
    }

}
