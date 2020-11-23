package suzanasavic.github.com.android.postitapp.ui.post_list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import suzanasavic.github.com.android.postitapp.data.database.DatabasePostsRepository
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.data.network.ApiPostsRepository
import java.util.*


class PostListViewModel(application: Application) : ViewModel() {

    private lateinit var posts: ArrayList<Post>
    private val repository: DatabasePostsRepository = DatabasePostsRepository(application)
    private var liveDataPosts: LiveData<List<Post>>

    init {
        liveDataPosts = getAllPosts()
    }

    fun getAllPosts() : LiveData<List<Post>> {
        liveDataPosts = repository.getAllPosts()
        return liveDataPosts
    }

    suspend fun fetchAllPosts() {
        posts = ApiPostsRepository().getPosts()
        repository.addNewPosts(posts)
    }

    fun refreshPosts() : Boolean{
        return if(getPosts().isEmpty()){
            false
        }else {
            repository.deleteAllPosts()
            false
        }
    }

    private fun getPosts() : ArrayList<Post>{
        val liveData = MutableLiveData<List<Post>>()
        liveData.value = liveDataPosts.value
        posts = liveData.value as ArrayList<Post>
        return posts
    }
}