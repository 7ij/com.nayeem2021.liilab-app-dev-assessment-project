package com.nayeem2021.liilab_app_dev_assesment_project.presentation

import androidx.lifecycle.ViewModel
import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.SessionRepositoryImplementationInRam
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.AuthStatus
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.SessionManagerImpl
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.LoginValidityCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val loginValidityCheck: LoginValidityCheckUseCase
) : ViewModel() {
    val liveData = SessionManagerImpl.liveData
    private var token : String ?= null
    init {
        CoroutineScope(Dispatchers.IO).launch {
            SessionManagerImpl.init(SessionRepositoryImplementationInRam())
            token = SessionManagerImpl.getToken()
            if(token != null) {
                loginValidityCheck(token!!)
            } else {
                SessionManagerImpl.liveData.postValue(AuthStatus.LoggedOut)
            }
        }
    }
}