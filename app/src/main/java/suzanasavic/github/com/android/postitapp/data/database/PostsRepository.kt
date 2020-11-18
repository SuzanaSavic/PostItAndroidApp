package suzanasavic.github.com.android.postitapp.data.database

import android.app.Application
import suzanasavic.github.com.android.postitapp.data.entities.Post

/**
 * Created by suzana.savic on 11/17/2020.
 */
class PostsRepository(applicationContext: Application) : BaseRepository {

    private var postsDao: PostsDao

    init {
        val postsDatabase = PostsDatabase.getInstance(applicationContext.applicationContext)
        postsDao = postsDatabase.postsDao
    }

    override fun addNewPosts(post: ArrayList<Post>) {
        return postsDao.addNewPosts(post)
    }
}