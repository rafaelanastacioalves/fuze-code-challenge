
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Team
import com.example.rafaelanastacioalves.moby.domain.entities.Resource


class AppRepository(
    var apiClient: APIClient) {

    suspend fun getUpComingMatchList(): Resource<List<Match>> {
        return object : NetworkBoundResource<List<Match>, List<Match>>() {
            override suspend fun fecthFromHttp(): List<Match> {

                return apiClient.getUpComingMatchList()
            }

            override suspend fun getFromDB(): List<Match>? {
                return null
            }

            override fun saveIntoDB(resultData: List<Match>?) {}

        }.fromHttpOnly()
    }
    suspend fun getRunningMatchList(): Resource<List<Match>> {
        return object : NetworkBoundResource<List<Match>, List<Match>>() {
            override suspend fun fecthFromHttp(): List<Match> {

                return apiClient.getRunningMatchList()
            }

            override suspend fun getFromDB(): List<Match>? {
                return null
            }

            override fun saveIntoDB(resultData: List<Match>?) {}

        }.fromHttpOnly()
    }
    

    suspend fun matchDetails(requestId: String): Resource<List<Team>> {
        return object : NetworkBoundResource<List<Team>, List<Team>>() {
            override suspend fun fecthFromHttp(): List<Team> {
                return apiClient.getMatchDetails(requestId)
            }

            override suspend fun getFromDB():List<Team> {
                TODO("Not yet implemented")
            }

            override fun saveIntoDB(resultData: List<Team>?) {
                TODO("Not yet implemented")
            }

        }.fromHttpOnly()
    }
}