package suzanasavic.github.com.android.postitapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import suzanasavic.github.com.android.postitapp.data.database.DatabasePostsRepository

/**
 * Created by suzana.savic on 11/19/2020.
 */
class FiveMinutesBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val repository = DatabasePostsRepository(App.instance)
        repository.deleteAllPosts()
    }

}