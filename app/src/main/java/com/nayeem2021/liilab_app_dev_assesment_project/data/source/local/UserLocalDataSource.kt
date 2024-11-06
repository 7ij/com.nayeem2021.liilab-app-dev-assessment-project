package com.nayeem2021.liilab_app_dev_assesment_project.data.source.local

import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData

private val data = mutableListOf<LoginData>(LoginData("143arza@gmail.com", "12345678"))
class UserLocalDataSource {
    fun login(loginData: LoginData) : Boolean {
        return data.contains(loginData)
    }
}