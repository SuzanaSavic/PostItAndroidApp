package suzanasavic.github.com.android.postitapp.ui.post_details

import android.app.Application
import androidx.lifecycle.ViewModel
import suzanasavic.github.com.android.postitapp.data.database.DatabasePostsRepository
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.data.entities.User
import suzanasavic.github.com.android.postitapp.data.network.ApiPostsRepository
import suzanasavic.github.com.android.postitapp.data.network.ApiUserRepository

class PostDetailsViewModel(private val post: Post, application: Application) : ViewModel() {

    private val repository: DatabasePostsRepository = DatabasePostsRepository(application)


    suspend fun getUser() : User{
        return ApiUserRepository().getUser(post.userId)
    }

    fun deletePost() {
        repository.deletePostWithId(post.id)
    }
}