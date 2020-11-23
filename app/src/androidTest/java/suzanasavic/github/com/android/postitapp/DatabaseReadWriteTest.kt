package suzanasavic.github.com.android.postitapp

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.base.Predicates.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import suzanasavic.github.com.android.postitapp.data.database.PostsDao
import suzanasavic.github.com.android.postitapp.data.database.PostsDatabase
import suzanasavic.github.com.android.postitapp.data.entities.Post
import java.io.IOException

/**
 * Created by suzana.savic on 11/23/2020.
 */
@RunWith(AndroidJUnit4::class)
class DatabaseReadWriteTest {
    private lateinit var postsDao: PostsDao
    private lateinit var database: PostsDatabase

    @Before
    fun createDatabase(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, PostsDatabase::class.java).build()
        postsDao = database.postsDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun write_post_and_read_Test() {
        val post : Post = Post("body", 0, "title", 0)
        postsDao.insert(post)
        val byTitle = postsDao.findPostsByTitle("title")
        Assert.assertEquals(byTitle[0], post)
    }
}