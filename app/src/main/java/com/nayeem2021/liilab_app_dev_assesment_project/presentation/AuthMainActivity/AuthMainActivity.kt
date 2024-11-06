package com.nayeem2021.liilab_app_dev_assesment_project.presentation.AuthMainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthMainActivity : AppCompatActivity() {
    private var _binding : ActivityAuthBinding ?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}