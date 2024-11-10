package com.nayeem2021.liilab_app_dev_assesment_project.data.repository

import com.nayeem2021.liilab_app_dev_assesment_project.data.model.ActionResponse
import com.nayeem2021.liilab_app_dev_assesment_project.data.model.LoginResponse
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.domain.model.RegistrationStatus
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val localDataSource: UserLocalDataSource) :
    AuthRepository {
    override suspend fun login(loginData: LoginData): LoginResponse {
        val loginResult = localDataSource.login(loginData)
        return loginResult
    }

    override suspend fun register(profileData: ProfileData): RegistrationStatus {
        val registerResult = localDataSource.register(profileData)
        return if (registerResult) RegistrationStatus.Success
        else RegistrationStatus.Failure("Something went wrong")
    }

    override suspend fun checkLoginValidity(token: String): Boolean {
        return localDataSource.isTokenValid(token)
    }

    override suspend fun performFakeAction(token: String): ActionResponse<Any> {
        val actionResult = localDataSource.performOperationWithToken(token)
        return when (actionResult) {
            "SUCCESS" -> return ActionResponse.Success<Any>("SUCCESS")
            "INVALID_TOKEN" -> return ActionResponse.InvalidToken("Token Expired")
            else -> return ActionResponse.Error("Something went wrong")
        }
    }
}