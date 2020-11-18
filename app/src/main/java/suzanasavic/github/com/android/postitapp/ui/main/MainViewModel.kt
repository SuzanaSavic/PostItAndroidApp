package suzanasavic.github.com.android.postitapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import suzanasavic.github.com.android.postitapp.data.database.PostsRepository
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.data.network.GetPostsRepository

class MainViewModel(application: Application) : ViewModel() {

    private val repository: PostsRepository = PostsRepository(application)

    private val _postList = MutableLiveData<List<Post>>()
    private val postList: LiveData<List<Post>>
        get() = _postList

    fun getAllPosts() : LiveData<List<Post>> {
        return repository.getAllPosts()
    }

    suspend fun fetchAllPosts() {
        val posts = GetPostsRepository().getPosts()
        repository.addNewPosts(posts)
    }

    fun deleteAllDataFromDatabase() {
        repository.deleteAllPosts()
    }
}