package suzanasavic.github.com.android.postitapp.ui.post_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import suzanasavic.github.com.android.postitapp.App
import suzanasavic.github.com.android.postitapp.R
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.data.entities.User
import suzanasavic.github.com.android.postitapp.databinding.PostDetailsFragmentBinding


class PostDetailsFragment: Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
    private val args: PostDetailsFragmentArgs by navArgs()
    private lateinit var post: Post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PostDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.post_details_fragment, container, false)

        post = args.post
        viewModel = ViewModelProvider(this, PostDetailsViewModelFactory(post, App.instance)).get(
            PostDetailsViewModel::class.java
        )
        lifecycleScope.launch {
            try {
                val user: User = viewModel.getUser()
                binding.user = user
                binding.post = post
            }catch (e: Exception){
                Snackbar.make(
                    binding.detailsMain,
                    getString(R.string.general_error_message),
                    Snackbar.LENGTH_LONG
                ).show()
                binding.progressBar.visibility = GONE
            }
        }
        binding.btnDeletePost.setOnClickListener {

            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle(resources.getString(R.string.alert_title))
                    .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.delete)) { dialog, _ ->
                        viewModel.deletePost()
                        dialog.dismiss()
                        activity?.onBackPressed()
                    }
                    .show()
            }
        }
        activity?.title = getString(R.string.details)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        super.onResume()
    }

    override fun onStop() {
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)
        super.onStop()
    }
}