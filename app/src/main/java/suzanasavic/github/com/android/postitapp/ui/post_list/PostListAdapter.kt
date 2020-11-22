package suzanasavic.github.com.android.postitapp.ui.post_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import suzanasavic.github.com.android.postitapp.R
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.databinding.RecyclerviewPostItemBinding

/**
 * Created by suzana.savic on 11/18/2020.
 */
class PostListAdapter(private val context: Context, private val postList: List<Post>, private val postItemClickListener : PostItemClickListener) : RecyclerView.Adapter<PostListAdapter.PostListViewHolder>(){

    private var lastPosition = -1

    inner class PostListViewHolder(val recyclerViewPostListItemBinding: RecyclerviewPostItemBinding) : RecyclerView.ViewHolder(
        recyclerViewPostListItemBinding.root
    )

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
        setAnimation(holder.itemView, position)
        holder.itemView.setOnClickListener {
            postItemClickListener.onPostItemClicked(holder.itemView, postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    interface PostItemClickListener {
        fun onPostItemClicked(post1: View, post: Post)
    }
}