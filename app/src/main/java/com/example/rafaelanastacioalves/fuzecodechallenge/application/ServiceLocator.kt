
import android.content.Context


object ServiceLocator {

    @Volatile
    private var appRepository: AppRepository? = null

    fun provideAppRespository(context: Context) : AppRepository {
        synchronized(this) {
            return appRepository ?: createAppRepository(context)
        }
    }

    private fun createAppRepository(context: Context) : AppRepository {
        return AppRepository(createApiClient(context))
    }


    private fun createApiClient(context: Context): APIClient {
        return ServiceGenerator.createService(APIClient::class.java)
    }

}