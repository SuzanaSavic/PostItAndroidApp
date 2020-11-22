package suzanasavic.github.com.android.postitapp.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import suzanasavic.github.com.android.postitapp.data.entities.Post

/**
 * Created by suzana.savic on 11/17/2020.
 */
class DatabasePostsRepository(applicationContext: Application) : BaseRepository {

    private var postsDao: PostsDao

    init {
        val postsDatabase = PostsDatabase.getInstance(applicationContext.applicationContext)
        postsDao = postsDatabase.postsDao
    }

    override fun addNewPosts(post: ArrayList<Post>) {
        return postsDao.addNewPosts(post)
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        return postsDao.getAllPosts()
    }

    override fun deleteAllPosts(){
        return postsDao.deleteAll()
    }

    override fun deletePostWithId(id: Int) {
        return postsDao.deletePostWithId(id)
    }


}