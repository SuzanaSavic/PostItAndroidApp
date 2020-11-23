package suzanasavic.github.com.android.postitapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import suzanasavic.github.com.android.postitapp.Constants.Companion.MAIN_URL
import suzanasavic.github.com.android.postitapp.data.network.ApiPostsRepository
import suzanasavic.github.com.android.postitapp.ui.post_list.PostListViewModel

/**
 * Created by suzana.savic on 11/23/2020.
 */
@RunWith(AndroidJUnit4::class)
class ApiTest {

    @Test
    fun posts_repo_retrieves_expected_data_Test() =  runBlocking<Unit>{
        val repository = ApiPostsRepository()
        val dataReceived = repository.getPosts()

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, 100)
    }

    @Test
    fun post_list_view_model_populated_expected_data_Test() =  runBlocking<Unit>{

        val postListViewModel = PostListViewModel(App.instance)
        val dataReceived = postListViewModel.fetchAllPostsForTest()

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, 100)
    }
}