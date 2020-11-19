package suzanasavic.github.com.android.postitapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import suzanasavic.github.com.android.postitapp.Constants.Companion.FIVE_MINUTES
import suzanasavic.github.com.android.postitapp.data.database.PostsRepository

/**
 * Created by suzana.savic on 11/19/2020.
 */
class FiveMinutesBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val repository = PostsRepository(App.instance)
        repository.deleteAllPosts()

        context?.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )?.edit()?.putBoolean(FIVE_MINUTES, true)?.apply()
    }

}