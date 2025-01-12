package com.nayeem2021.liilab_app_dev_assesment_project.domain.repository

import com.nayeem2021.liilab_app_dev_assesment_project.data.model.ActionResponse
import com.nayeem2021.liilab_app_dev_assesment_project.data.model.LoginResponse
import com.nayeem2021.liilab_app_dev_assesment_project.domain.model.RegistrationStatus
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

interface AuthRepository {
    fun login(loginData: LoginData): LoginResponse
    fun register(profileData: ProfileData): RegistrationStatus
    fun checkLoginValidity(token: String): Boolean
    fun performFakeAction(token: String): ActionResponse<Any>
}