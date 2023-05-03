import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import retrofit2.http.POST
import retrofit2.http.Path

interface APIClient {

    @POST("/trip-packages")
    suspend fun getMatchList(): List<Match>;

    @POST("/trip-packages/{entityID}")
    suspend fun getMatchDetails(@Path("entityID") id: String): Match


}
