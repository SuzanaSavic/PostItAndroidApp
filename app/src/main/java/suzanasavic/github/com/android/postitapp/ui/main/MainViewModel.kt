package suzanasavic.github.com.android.postitapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import suzanasavic.github.com.android.postitapp.data.database.PostsRepository
import suzanasavic.github.com.android.postitapp.data.entities.Post

class MainViewModel(application: Application) : ViewModel() {

    private val repository: PostsRepository = PostsRepository(application)

    fun getAllPosts() : LiveData<List<Post>> {
        return repository.getAllPosts()
    }
}