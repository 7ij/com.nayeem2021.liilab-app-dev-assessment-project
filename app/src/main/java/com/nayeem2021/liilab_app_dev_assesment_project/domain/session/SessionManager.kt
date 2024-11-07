package com.nayeem2021.liilab_app_dev_assesment_project.domain.session

interface SessionManager {
    fun getToken() : String?
    fun saveToken(token: String)
}