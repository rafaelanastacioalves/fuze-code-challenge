import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import kotlinx.coroutines.flow.FlowCollector

class MatchListingInteractor(val appRepository: AppRepository) :
    Interactor<Resource<List<Match>>, MatchListingInteractor.RequestValues>() {


    override suspend fun run(
        requestValues: RequestValues?,
        flowCollector: FlowCollector<Resource<List<Match>>>,
    ) {
        flowCollector.emit(Resource.loading())
        val upComingMatchResource = appRepository.getUpComingMatchList()
        val runningMatchResource = appRepository.getRunningMatchList()

        if (upComingMatchResource.status == Resource.Status.SUCCESS &&
                runningMatchResource.status == Resource.Status.SUCCESS){
            val upComingList : List<Match> = upComingMatchResource.data.orEmpty()
            val runningList = runningMatchResource.data.orEmpty()
            val finalList = runningList + upComingList
            flowCollector.emit(Resource.success(finalList))
            return
        }
        flowCollector.emit(Resource.error(Resource.Status.GENERIC_ERROR, message = "One of the Repository fetch failed: \n " +
                "UpComing error: ${upComingMatchResource.message} \n " +
                "Running error: ${runningMatchResource.message}"))
    }




    class RequestValues :
        Interactor.RequestValues// in this case we don't need nothing for this use case
}
