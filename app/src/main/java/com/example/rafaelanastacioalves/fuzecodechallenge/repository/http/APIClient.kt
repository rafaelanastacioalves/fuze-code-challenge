import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Team
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient {

    @GET("/csgo/matches/upcoming")
    suspend fun getMatchList(): List<Match>;


    @GET("/csgo/teams")
    suspend fun getMatchDetails(
        @Query("filter[id]") filterId: String?
    ): List<Team>

}
