import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIClient {

    @GET("/csgo/matches/upcoming")
    suspend fun getMatchList(): List<Match>;

    @GET("/csgo/matches/{matchId}")
    suspend fun getMatchDetails(@Path("matchId") id: String): Match


}
