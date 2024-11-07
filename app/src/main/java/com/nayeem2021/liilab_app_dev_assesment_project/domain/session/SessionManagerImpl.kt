package com.nayeem2021.liilab_app_dev_assesment_project.domain.session

import androidx.lifecycle.MutableLiveData
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.SessionRepository

object SessionManagerImpl : SessionManager {
    private var _token : String ?= null
    private lateinit var sessionRepository : SessionRepository
    private var initialized = false
    val liveData = MutableLiveData<AuthStatus>()

    fun init(sessionRepository: SessionRepository) {
        this.sessionRepository = sessionRepository
        initialized = true
        retrieveToken()
    }

    override fun getToken() : String? {
        checkInitialized()
        return _token
    }

    override fun saveToken(token: String) {
        checkInitialized()
        sessionRepository.saveToken(token)
        this._token = token
    }

    private fun retrieveToken() {
        checkInitialized()
        _token = sessionRepository.retrieveToken()
    }

    fun clearToken() {
        checkInitialized()
        sessionRepository.deleteToken()
        _token = null
        liveData.postValue(AuthStatus.LoggedOut)
    }

    private fun checkInitialized() {
        if(!initialized) {
            throw IllegalStateException("SessionManager not initialized")
        }
    }

    fun setAuthStatusLoggedIn() {
        liveData.postValue(AuthStatus.LoggedIn)
    }

}

sealed class AuthStatus {
    data object LoggedIn: AuthStatus()
    data object LoggedOut: AuthStatus()
}
