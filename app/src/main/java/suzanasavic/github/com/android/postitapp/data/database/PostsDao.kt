package suzanasavic.github.com.android.postitapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import suzanasavic.github.com.android.postitapp.data.entities.Post

/**
 * Created by suzana.savic on 11/17/2020.
 */

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewPosts(posts: ArrayList<Post>)

    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<Post>>
}