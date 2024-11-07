package com.nayeem2021.liilab_app_dev_assesment_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.ActivityMainComponenetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComponentActivity : AppCompatActivity() {
    private var _binding: ActivityMainComponenetBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainComponenetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost = supportFragmentManager
            .findFragmentById(binding.mainComponentNavHostFragment.id) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNavigationView.setupWithNavController(navController)

    }
}