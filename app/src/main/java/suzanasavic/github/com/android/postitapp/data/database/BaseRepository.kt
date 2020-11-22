package suzanasavic.github.com.android.postitapp.data.database

import androidx.lifecycle.LiveData
import suzanasavic.github.com.android.postitapp.data.entities.Post

/**
 * Created by suzana.savic on 11/17/2020.
 */
interface BaseRepository {
    fun addNewPosts(post: ArrayList<Post>)
    fun getAllPosts() : LiveData<List<Post>>
    fun deleteAllPosts()
    fun deletePostWithId(id : Int)
}