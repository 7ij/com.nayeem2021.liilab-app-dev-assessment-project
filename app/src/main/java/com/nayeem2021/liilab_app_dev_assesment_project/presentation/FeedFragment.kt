package com.nayeem2021.liilab_app_dev_assesment_project.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.DummyData
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.HomePageRecentEventModel
import com.nayeem2021.liilab_app_dev_assesment_project.domain.PaddingInBetweenRecyclerViewDecorator
import com.nayeem2021.liilab_app_dev_assesment_project.R
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentFeedBinding
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.BirthdayModel
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.CreatePostModel
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.HomePageStoriesModel
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.PostModel

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    val userPostsDataSet: List<PostModel> = DummyData.getPosts()
    val recentEventDataSet: HomePageRecentEventModel = DummyData.getRecentEvents()

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
        val dataSet = listOf(
            HomePageStoriesModel(DummyData.getStories()),
            CreatePostModel(),
            userPostsDataSet[0],
            recentEventDataSet,
            userPostsDataSet[1],
            BirthdayModel()
        )
        binding.homePageRecyclerView.adapter =
            HomePageRecyclerViewAdapter(dataSet, ::navigateToCreatePostFragment)
        binding.homePageRecyclerView.addItemDecoration(PaddingInBetweenRecyclerViewDecorator(8))
    }
}