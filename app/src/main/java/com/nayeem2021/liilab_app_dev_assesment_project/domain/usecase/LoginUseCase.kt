package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.SessionManagerImpl
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun requestLogin(loginData: LoginData): Boolean {
        val loginResponse =  authRepository.login(loginData)
        if(loginResponse.status == true) {
            val token = loginResponse.token!!
            SessionManagerImpl.saveToken(token)
        }
        return loginResponse.status
    }
}