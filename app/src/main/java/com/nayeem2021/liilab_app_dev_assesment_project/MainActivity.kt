package com.nayeem2021.liilab_app_dev_assesment_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        initViews()
        setContentView(binding.root)
    }


    private fun initViews() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    replaceFragment(FeedFragment())
                }

                R.id.my_community_page -> {
                    replaceFragment(MyCommunityFragment())
                }

                else -> {
                    true
                }
            }

        }

        binding.bottomNavigationView.selectedItemId = R.id.home_page
    }

    private fun replaceFragment(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}