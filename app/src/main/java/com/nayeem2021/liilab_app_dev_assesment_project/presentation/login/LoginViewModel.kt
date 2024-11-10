package com.nayeem2021.liilab_app_dev_assesment_project.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.LoginUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {
    private var _loginData = MutableLiveData<Boolean>()
    val loginData = _loginData
    fun requestLogin(loginData: LoginData) {

        CoroutineScope(Dispatchers.IO).launch {
            val res = async {
                loginUseCase.requestLogin(loginData)
            }.await()
            CoroutineScope(Dispatchers.Main).launch {
                _loginData.value = res
            }
        }
    }
}