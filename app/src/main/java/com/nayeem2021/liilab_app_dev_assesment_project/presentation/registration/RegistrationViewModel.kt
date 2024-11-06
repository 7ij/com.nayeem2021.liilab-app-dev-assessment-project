package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

class RegistrationViewModel(private val registrationUseCase: RegistrationUseCase) : ViewModel() {
    private val _registrationUiStatus = MutableLiveData<String>()
    val registrationUiStatus = _registrationUiStatus
    fun requestRegistration(profileData: ProfileData) {
        val result = registrationUseCase(profileData)
        _registrationUiStatus.value = result
    }
}
class RegistrationViewModelFactory(private val registrationUseCase: RegistrationUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(registrationUseCase) as T
    }
}