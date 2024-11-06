package com.nayeem2021.liilab_app_dev_assesment_project.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.LoginUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData

class LoginViewModel(val loginUseCase: LoginUseCase) : ViewModel() {
    private var _loginData = MutableLiveData<String>()
    val loginData = _loginData
    fun requestLogin(loginData: LoginData) {
        val loginResult = loginUseCase.requestLogin(loginData)
        _loginData.value = loginResult
    }

}
class LoginViewModelFactory(
    private val loginUseCase: LoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginUseCase) as T
    }
}