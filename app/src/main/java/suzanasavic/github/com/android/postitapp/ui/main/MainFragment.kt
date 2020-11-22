package suzanasavic.github.com.android.postitapp.ui.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import suzanasavic.github.com.android.postitapp.App
import suzanasavic.github.com.android.postitapp.Constants.Companion.FIVE_MINUTES
import suzanasavic.github.com.android.postitapp.FiveMinutesBroadcastReceiver
import suzanasavic.github.com.android.postitapp.R
import suzanasavic.github.com.android.postitapp.data.entities.Post
import java.lang.Exception
import java.util.*

class MainFragment : Fragment(), PostListAdapter.PostItemClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var main: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel
    private lateinit var swipeToRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        swipeToRefresh = root.findViewById(R.id.swipeToRefresh)
        main = root.findViewById(R.id.main)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val app = App.instance
        viewModel = ViewModelProvider(this, MainViewModelModelFactory(app)).get(MainViewModel::class.java)
        viewModel.getAllPosts().observe(
            viewLifecycleOwner, { postList ->
                if (postList.isEmpty()) {
                    val defaultValue = context?.getSharedPreferences(
                        context!!.getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE)?.getBoolean(FIVE_MINUTES, false)

                   /*** In task description it is sad:  Posts should be stored locally with a validity time of 5 minutes.
                    I assume that after 5 minutes app need to fetch new data
                    And that is how app works now
                    But if after 5 minutes app needs to delete data and wait for user to swipe to fetch new data you just need to uncomment code below :)
                    And comment 72. line.
                    It will work smoothly**/

                   /* if(!defaultValue!!) {
                        //if user swiped on already deleted postList fetch data
                       fetchPostsAndSetAlarm()
                    }
                    else{
                        context?.getSharedPreferences(context?.getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE)?.edit()?.putBoolean(FIVE_MINUTES, false)?.apply()
                       setupRecyclerView(postList)
                    }*/
                    fetchPostsAndSetAlarm()
                } else {
                    setupRecyclerView(postList)
                }
            })

        swipeToRefresh.setOnRefreshListener{
            if(!viewModel.refreshPosts()){
                fetchPostsAndSetAlarm()
            }
        }
    }

    private fun fetchPostsAndSetAlarm() {
        lifecycleScope.launch {
            try {
                viewModel.fetchAllPosts()
                val calendar = Calendar.getInstance()
                calendar.time = Date()
                calendar.add(Calendar.MINUTE, 5)
                startAlarm(calendar)
            }catch (e: Exception){
                Snackbar.make(main, getString(R.string.general_error_message), Snackbar.LENGTH_LONG).show()
                swipeToRefresh.isRefreshing = false
            }
        }
    }

    private fun setupRecyclerView(postList: List<Post>) {
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = PostListAdapter(requireContext(), postList, this)
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun startAlarm(calendar: Calendar) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, FiveMinutesBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    override fun onPostItemClicked(post: Post) {
        //todo navigate to fragment and fetch data
    }
}