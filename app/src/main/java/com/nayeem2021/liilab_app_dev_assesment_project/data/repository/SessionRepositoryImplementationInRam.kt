package com.nayeem2021.liilab_app_dev_assesment_project.data.repository

import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.SessionRepository

//say this is my database which will store session token for my app
private var TOKEN : String ?= null

class SessionRepositoryImplementationInRam : SessionRepository {
    override fun retrieveToken(): String? {
        return TOKEN
    }

    override fun deleteToken() {
        TOKEN = null
    }

    override fun saveToken(token: String) {
        TOKEN = token
    }
}