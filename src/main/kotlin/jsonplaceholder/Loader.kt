package jsonplaceholder

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class Loader {

    private val jsonPlaceholderApiServe by lazy {
        JSONPlaceholderApiService.create()
    }

    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val saver = Saver(gson)

    suspend fun loadPosts() {

        val request = jsonPlaceholderApiServe.postsList()
        var response: Response<List<JSONPlaceholderPost>>

        runBlocking {
            response = request.await()

            if (response.isSuccessful) {
                saver.saveToFiles(response.body()!!)
            } else {
                handleError(response.message())
            }
        }
    }

    private fun handleError(error: String?) {
        System.out.println(error)
    }
}
