import com.example.rafaelanastacioalves.fuzecodechallenge.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val appInterceptor = AppInterceptor()


    fun <S> createService(serviceClass: Class<S>): S {

        val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        val httpClient = OkHttpClient.Builder()

        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val retrofit = builder.client(httpClient
                .addInterceptor(appInterceptor)
                .addInterceptor(interceptor)
                .build()).build()
        return retrofit.create(serviceClass)
    }

}
