package com.nayeem2021.liilab_app_dev_assesment_project.data.repository

import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData

class AuthRepositoryImpl(private val localDataSource: UserLocalDataSource) : AuthRepository {
    override fun login(loginData: LoginData): String {
        val loginResult = localDataSource.login(loginData)
        return if (loginResult) "SUCCESS" else "INCORRECT USER NAME OR PASSWORD"
    }
}