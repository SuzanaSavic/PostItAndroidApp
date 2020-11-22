package suzanasavic.github.com.android.postitapp.ui.post_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import suzanasavic.github.com.android.postitapp.data.entities.Post

/**
 * Created by suzana.savic on 11/22/2020.
 */
class PostDetailsViewModelFactory(private val post : Post, private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailsViewModel(post, application) as T
    }
}