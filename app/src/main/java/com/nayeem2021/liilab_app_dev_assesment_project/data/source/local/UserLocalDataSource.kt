package com.nayeem2021.liilab_app_dev_assesment_project.data.source.local

import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

private val data = mutableListOf<LoginData>(LoginData("143arza@gmail.com", "12345678"))
private val userData = mutableListOf<ProfileData>()

class UserLocalDataSource {
    fun login(loginData: LoginData): Boolean {
        return data.contains(loginData)
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