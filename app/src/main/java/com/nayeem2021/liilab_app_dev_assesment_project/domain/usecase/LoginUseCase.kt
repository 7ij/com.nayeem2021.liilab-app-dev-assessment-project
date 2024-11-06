package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import android.util.Log
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData

class LoginUseCase(private val authRepository: AuthRepository) {
    fun requestLogin(loginData: LoginData): String {
        return authRepository.login(loginData)
    }
}