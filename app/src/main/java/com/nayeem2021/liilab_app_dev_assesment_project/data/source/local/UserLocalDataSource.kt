package com.nayeem2021.liilab_app_dev_assesment_project.data.source.local

import com.nayeem2021.liilab_app_dev_assesment_project.data.model.LoginResponse
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import java.util.UUID
import javax.inject.Inject

private val data = mutableListOf<LoginData>(LoginData("143arza@gmail.com", "Myn@meikhan01"))
private val userData = mutableListOf<ProfileData>()
private val tokens = mutableListOf<String>()

class UserLocalDataSource @Inject constructor() {
    fun login(loginData: LoginData): LoginResponse {
        return if(data.contains(loginData)) {
            val newToken = generateToken()
            tokens.add(newToken!!)
            LoginResponse(true, newToken)
        } else {
            LoginResponse(false, null)
        }
    }

    fun logout(token: String): Boolean {
        return tokens.remove(token)
    }

    fun isTokenValid(token: String): Boolean {
        return tokens.contains(token)
    }

    private fun generateToken(): String? {
        return UUID.randomUUID().toString()
    }

    fun register(profileData: ProfileData): Boolean {
        return if (alreadyExist(profileData)) {
            false
        } else {
            userData.add(profileData)
            data.add(LoginData(profileData.username, profileData.password))
            return true
        }
    }

    private fun alreadyExist(profileData: ProfileData): Boolean {
        return userData.contains(profileData)
    }
}