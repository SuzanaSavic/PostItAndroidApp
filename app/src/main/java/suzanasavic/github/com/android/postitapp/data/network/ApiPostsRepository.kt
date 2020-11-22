package suzanasavic.github.com.android.postitapp.data.network

import suzanasavic.github.com.android.postitapp.data.entities.Post
import java.io.IOException

/**
 * Created by suzana.savic on 11/17/2020.
 */
class ApiPostsRepository : SafeApiRequest() {
    suspend fun getPosts(): ArrayList<Post> {
        try {
            return apiRequest {
                Api().getAllPosts()
            }
        }catch (e: Exception){
            throw IOException()
        }
    }
}