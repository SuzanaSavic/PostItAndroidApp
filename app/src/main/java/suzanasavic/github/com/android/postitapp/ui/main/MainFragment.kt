package suzanasavic.github.com.android.postitapp.ui.main

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import suzanasavic.github.com.android.postitapp.App
import suzanasavic.github.com.android.postitapp.R
import suzanasavic.github.com.android.postitapp.data.database.PostsRepository
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.data.network.GetPostsRepository
import java.util.ArrayList

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var posts: ArrayList<Post>
    private lateinit var viewModel: MainViewModel
    private lateinit var swipeToRefresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root =inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        swipeToRefresh = root.findViewById(R.id.swipeToRefresh)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val app = App.instance
        viewModel = ViewModelProvider(this, MainViewModelModelFactory(app)).get(MainViewModel::class.java)
        viewModel.getAllPosts().observe(viewLifecycleOwner, androidx.lifecycle.Observer { postList ->

            //check if database is empty
            //it it is empty make call and fetch new data
            if(postList.isEmpty()){
                lifecycleScope.launch {
                    viewModel.fetchAllPosts()
                    //set time for 5 minutes and delete data
                    //set flag to know if
                }
            }else {
                recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = PostListAdapter(requireContext(), postList)
                    swipeToRefresh.isRefreshing = false
                }
            }
        })

        swipeToRefresh.setOnRefreshListener{
            viewModel.deleteAllDataFromDatabase()
        }
    }
}