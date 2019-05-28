import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JSONPlaceholderApiService {

    @GET("/posts")
    fun postsList() : Deferred<Response<List<JSONPlaceholderPost>>> // Call<List<JSONPlaceholderPost>>

    companion object {
        fun create() : JSONPlaceholderApiService {

            val requestBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("http://jsonplaceholder.typicode.com")
                .build()

            return requestBuilder.create(JSONPlaceholderApiService::class.java)
        }
    }

}