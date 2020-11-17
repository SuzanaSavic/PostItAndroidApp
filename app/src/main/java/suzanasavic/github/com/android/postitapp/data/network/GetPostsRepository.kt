package suzanasavic.github.com.android.postitapp.data.network

import suzanasavic.github.com.android.postitapp.data.entities.Post

/**
 * Created by suzana.savic on 11/17/2020.
 */
class GetPostsRepository : SafeApiRequest() {
    suspend fun getPosts(): ArrayList<Post> {
        return apiRequest {
            Api().getAllPosts()
        }
    }
}