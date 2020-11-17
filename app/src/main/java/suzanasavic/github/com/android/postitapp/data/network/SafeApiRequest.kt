package suzanasavic.github.com.android.postitapp.data.network

import retrofit2.Response
import java.io.IOException

/**
 * Created by suzana.savic on 11/17/2020.
 */
abstract class SafeApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.toString()
            throw IOException(error.toString())
        }
    }
}