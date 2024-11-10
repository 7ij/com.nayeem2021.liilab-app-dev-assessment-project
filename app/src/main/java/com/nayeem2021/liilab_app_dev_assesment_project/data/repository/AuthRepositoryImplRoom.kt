package com.nayeem2021.liilab_app_dev_assesment_project.data.repository

import com.nayeem2021.liilab_app_dev_assesment_project.data.model.ActionResponse
import com.nayeem2021.liilab_app_dev_assesment_project.data.model.LoginResponse
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.Database
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.entity.User
import com.nayeem2021.liilab_app_dev_assesment_project.domain.model.RegistrationStatus
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class AuthRepositoryImplRoom @Inject constructor(private val roomDb: Database) : AuthRepository {
    override suspend fun login(loginData: LoginData): LoginResponse {
        val deffered = GlobalScope.async(Dispatchers.IO) {
            val allUsers = roomDb.userDao().getAllUser()
            for (user in allUsers) {
                if (user.userName == loginData.username &&
                    user.password == loginData.password
                )
                    return@async LoginResponse(token = "abc", status = true)
            }
            return@async LoginResponse(false, null)

        }
        return deffered.await()
    }

    override suspend fun register(profileData: ProfileData): RegistrationStatus {
        return GlobalScope.async(Dispatchers.IO) {
            val userId = roomDb.userDao().insertUser(
                User(
                    userName = profileData.username,
                    password = profileData.password
                )
            )
            return@async if (userId != -1L) RegistrationStatus.Success
            else RegistrationStatus.Failure("Failed creating user")

        }.await()
    }

    override suspend fun checkLoginValidity(token: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun performFakeAction(token: String): ActionResponse<Any> {
        TODO("Not yet implemented")
    }
}