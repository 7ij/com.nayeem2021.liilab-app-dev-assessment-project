package com.nayeem2021.liilab_app_dev_assesment_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    val commentDataSet: List<CommentModel> = listOf(
        CommentModel(
            "Swapan Bala",
            "9h ago",
            "Looks amazing and breathtaking. Been there, beautiful!",
            listOf(
                ReplyModel(
                    "Whitechapel Gallery",
                    "6h ago",
                    "Swapan Bala",
                    "Looks amazing and breathtaking. Been there, beautiful!",
                    "Thank you @Swapan Bala"
                ),
                ReplyModel(
                    "Md. Ashiqur Rahman Naeem",
                    "6h ago",
                    "Whitechapel Gallery",
                    "Thank you @Swapan Bala",
                    "No need to thak him. He is not good. @Whitechapel Gallery"
                )
            )
        ),
        CommentModel(
            "Rahim Badsha",
            "1h ago",
            "Looks amazing and breathtaking. Been there, beautiful!",
            listOf(
                ReplyModel(
                    "Whitechapel Gallery",
                    "6h ago",
                    "Swapan Bala",
                    "Looks amazing and breathtaking. Been there, beautiful!",
                    "Thank you @Swapan Bala"
                ),
                ReplyModel(
                    "Md. Ashiqur Rahman Naeem",
                    "6h ago",
                    "Whitechapel Gallery",
                    "Thank you @Swapan Bala",
                    "No need to thak him. He is not good. @Whitechapel Gallery"
                )
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun navigateToCreatePostFragment() {
        findNavController().navigate(R.id.action_feedFragment_to_createPostFragment)
    }

    private fun initViews() {
        val dataSet = listOf("a", "b", "c", "d", commentDataSet)
        binding.homePageRecyclerView.adapter =
            HomePageRecyclerViewAdapter(dataSet, ::navigateToCreatePostFragment)

    }
}