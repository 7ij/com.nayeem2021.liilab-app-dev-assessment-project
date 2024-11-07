package com.nayeem2021.liilab_app_dev_assesment_project.domain.repository

interface SessionRepository {
    fun retrieveToken() : String?
    fun deleteToken()
    fun saveToken(token : String)
}
