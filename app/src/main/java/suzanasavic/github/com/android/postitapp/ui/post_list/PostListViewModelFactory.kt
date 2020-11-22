package suzanasavic.github.com.android.postitapp.ui.post_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by suzana.savic on 11/18/2020.
 */
class PostListViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostListViewModel(application) as T
    }
}