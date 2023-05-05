import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Team
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.Interactor
import kotlinx.coroutines.flow.FlowCollector

class MatchDetailInteractor(
    private val appRepository: AppRepository,
) : Interactor<Resource<Match>, MatchDetailInteractor.RequestValues>() {

    class RequestValues(val match: Match) : Interactor.RequestValues

    override suspend fun run(
        requestValue: RequestValues?,
        flowCollector: FlowCollector<Resource<Match>>,
    ) {
        flowCollector.emit(Resource.loading())
        if ( requestValue == null){
            flowCollector.emit(Resource.error(Resource.Status.GENERIC_ERROR, message = "Match info not found"))
            return
        }

        if (requestValue.match.opponents.size < 2){
            flowCollector.emit(Resource.error(Resource.Status.GENERIC_ERROR, message = "Match with less than 2 opponents"))
            return
        }
        val match = requestValue.match
        val firstId = match.opponents[0].opponentDetails.id.toString()
        val secondId = match.opponents[1].opponentDetails.id.toString()
        val teamsListResource : Resource<List<Team>> = requestValue.match.let { matchResult ->
            appRepository.matchDetails(firstId+ ',' + secondId)

        }
        if(teamsListResource.status == Resource.Status.SUCCESS && teamsListResource.data!= null){
            val teamList = teamsListResource.data
            match.teamList = teamList
            flowCollector.emit(Resource.success(match))
            return
        }

        flowCollector.emit(Resource.error(Resource.Status.GENERIC_ERROR, message ="Something went wrong getting team list"))


    }

}
