package com.nayeem2021.liilab_app_dev_assesment_project
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding ?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}