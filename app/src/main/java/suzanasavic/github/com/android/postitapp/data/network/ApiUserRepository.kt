package suzanasavic.github.com.android.postitapp.data.network

import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.data.entities.User
import java.io.IOException

/**
 * Created by suzana.savic on 11/22/2020.
 */
class ApiUserRepository : SafeApiRequest() {
    suspend fun getUser(userId : Int): User {
        try {
            return apiRequest {
                Api().getUser(userId)
            }
        }catch (e: Exception){
            throw IOException()
        }
    }
}