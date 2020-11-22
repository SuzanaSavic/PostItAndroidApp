package suzanasavic.github.com.android.postitapp.ui.post_details

import android.content.ClipData.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import suzanasavic.github.com.android.postitapp.App
import suzanasavic.github.com.android.postitapp.Constants.Companion.POST_KEY
import suzanasavic.github.com.android.postitapp.R
import suzanasavic.github.com.android.postitapp.data.entities.Post
import suzanasavic.github.com.android.postitapp.data.entities.User
import suzanasavic.github.com.android.postitapp.databinding.PostDetailsFragmentBinding
import java.lang.Exception


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
                Snackbar.make(binding.main, getString(R.string.general_error_message), Snackbar.LENGTH_LONG).show()
                binding.progressBar.visibility = GONE
            }
        }
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }
        binding.btnDeletePost.setOnClickListener {

            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle(resources.getString(R.string.alert_title))
                    .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.delete)) { dialog, which ->
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
}