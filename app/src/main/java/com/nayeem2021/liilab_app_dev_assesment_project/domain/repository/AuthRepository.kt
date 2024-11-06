package com.nayeem2021.liilab_app_dev_assesment_project.domain.repository

import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

interface AuthRepository {
    fun login(loginData: LoginData) : String
    fun register(profileData: ProfileData) : String
}