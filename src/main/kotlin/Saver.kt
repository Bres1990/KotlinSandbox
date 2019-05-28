import com.google.gson.Gson
import java.io.File

class Saver(private val gson: Gson) {

    fun saveToFiles(list: List<JSONPlaceholderPost>) {

        for (post in list) {
            val filename = "File ".plus(list.indexOf(post)+1).plus(".json")
            val file = File(filename)
            file.appendText(gson.toJson(post))
        }
    }
}