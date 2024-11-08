package com.nayeem2021.liilab_app_dev_assesment_project.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.AuthStatus
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.AuthMainActivity.AuthMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        // Shall we do the following? I'm not sure though
        // Close MainActivity so it’s not in the back stack
        //        finish()
    }

    private fun initObserver() {
        val observer = Observer<AuthStatus> {
            Log.i("debugTag", "MainActivity::initObserver: $it")
            when (it) {
                AuthStatus.LoggedIn -> {
                    val intent = Intent(this, MainComponentActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                AuthStatus.LoggedOut -> {
                    val intent = Intent(this, AuthMainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
        mainActivityViewModel.liveData.observe(this, observer)
    }

}