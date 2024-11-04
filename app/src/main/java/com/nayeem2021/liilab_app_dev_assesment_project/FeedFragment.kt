package com.nayeem2021.liilab_app_dev_assesment_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private var _binding:FragmentFeedBinding ?= null
    private val binding get() = _binding!!
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
    private fun initViews() {
        val dataSet = listOf("a", "b", "c")
        binding.homePageRecyclerView.adapter = HomePageRecyclerViewAdapter(dataSet)
    }
}