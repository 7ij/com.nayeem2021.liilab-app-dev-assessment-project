package com.nayeem2021.liilab_app_dev_assesment_project.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nayeem2021.liilab_app_dev_assesment_project.R
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
        val navBar  = binding.bottomNavigationView
        navBar.setupWithNavController(navController)
        navBar.getOrCreateBadge(R.id.notificationFragment).number = 2
    }
}