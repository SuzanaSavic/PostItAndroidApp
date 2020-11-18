package suzanasavic.github.com.android.postitapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import suzanasavic.github.com.android.postitapp.R
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.databinding.RecyclerviewPostItemBinding

/**
 * Created by suzana.savic on 11/18/2020.
 */
class PostListAdapter (private val postList: List<Post>) : RecyclerView.Adapter<PostListAdapter.PostListViewHolder>(){

    inner class PostListViewHolder(val recyclerViewPostListItemBinding: RecyclerviewPostItemBinding) : RecyclerView.ViewHolder(recyclerViewPostListItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= PostListViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_post_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.recyclerViewPostListItemBinding.post = postList[position]
    }

    override fun getItemCount(): Int = postList.size
}