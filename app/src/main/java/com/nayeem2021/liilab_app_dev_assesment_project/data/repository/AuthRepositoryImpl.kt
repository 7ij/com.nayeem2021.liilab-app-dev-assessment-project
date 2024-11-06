package com.nayeem2021.liilab_app_dev_assesment_project.data.repository

import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

class AuthRepositoryImpl(private val localDataSource: UserLocalDataSource) : AuthRepository {
    override fun login(loginData: LoginData): String {
        val loginResult = localDataSource.login(loginData)
        return if (loginResult) "SUCCESS" else "INCORRECT USER NAME OR PASSWORD"
    }

    override fun register(profileData: ProfileData): String {
        val registerResult = localDataSource.register(profileData)
        return if(registerResult) "SUCCESS" else "SOMETHING WENT WRONG"
    }
}