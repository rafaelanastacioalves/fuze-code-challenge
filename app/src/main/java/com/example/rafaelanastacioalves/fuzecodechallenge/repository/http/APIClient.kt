import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Team
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    @GET("/csgo/matches/upcoming")
    suspend fun getUpComingMatchList(): List<Match>;

    @GET("/csgo/matches/running")
    suspend fun getRunningMatchList(): List<Match>;


    @GET("/csgo/teams")
    suspend fun getMatchDetails(
        @Query("filter[id]") filterId: String?
    ): List<Team>

}
