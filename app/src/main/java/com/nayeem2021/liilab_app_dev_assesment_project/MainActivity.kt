package com.nayeem2021.liilab_app_dev_assesment_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.AuthRepositoryImpl
import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.SessionRepositoryImplementationInRam
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.SessionManager
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.SessionManagerImpl
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.LoginValidityCheckUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.AuthMainActivity.AuthMainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    @Inject
    lateinit var loginValidityCheck: LoginValidityCheckUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SessionManagerImpl.init(SessionRepositoryImplementationInRam())
        sessionManager = SessionManagerImpl
        val token = sessionManager.getToken()

        if (token != null && loginValidityCheck(token!!)) {
            val intent = Intent(this, MainComponentActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } else {
            val intent = Intent(this, AuthMainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        // Shall we do the following? I'm not sure though
        // Close MainActivity so it’s not in the back stack
        //        finish()
    }

}