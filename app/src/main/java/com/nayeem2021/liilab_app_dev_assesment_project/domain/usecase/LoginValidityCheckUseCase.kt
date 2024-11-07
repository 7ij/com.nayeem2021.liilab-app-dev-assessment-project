package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.SessionManagerImpl
import javax.inject.Inject

class LoginValidityCheckUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(token: String) : Boolean {
        val isValid = authRepository.checkLoginValidity(token)
        if(!isValid)
            SessionManagerImpl.clearToken()
        else
            SessionManagerImpl.setAuthStatusLoggedIn()

        return isValid
    }
}