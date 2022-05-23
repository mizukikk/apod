import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

object TestFileUtils {
    fun readText(name: String): String {
        val result = try {
            val inputStream = javaClass.classLoader?.getResourceAsStream(name)
            BufferedReader(InputStreamReader(inputStream))
                .readText()
        } catch (e: Exception) {
            ""
        }
        return result
    }
}