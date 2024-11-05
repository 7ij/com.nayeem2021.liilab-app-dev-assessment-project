package com.nayeem2021.liilab_app_dev_assesment_project.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private var _binding : FragmentRegistrationBinding ?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }
}