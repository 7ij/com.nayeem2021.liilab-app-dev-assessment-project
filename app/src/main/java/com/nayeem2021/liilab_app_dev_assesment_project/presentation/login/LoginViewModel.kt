package com.nayeem2021.liilab_app_dev_assesment_project.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.LoginUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {
    private var _loginData = MutableLiveData<Boolean>()
    val loginData = _loginData
    fun requestLogin(loginData: LoginData) {
        val loginResult = loginUseCase.requestLogin(loginData)
        _loginData.value = loginResult
    }

}