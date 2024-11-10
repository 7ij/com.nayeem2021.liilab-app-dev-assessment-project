package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nayeem2021.liilab_app_dev_assesment_project.domain.model.RegistrationStatus
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationDataValidationUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val requestRegistration: RegistrationUseCase,
    private val formDataValidate: RegistrationDataValidationUseCase
) : ViewModel() {

    private val _uiEffects = MutableSharedFlow<RegistrationUiEffects>()
    val uiEffects = _uiEffects

    fun handleEvent(event: RegistrationUiEvents) {
        viewModelScope.launch {
            when (event) {
                is RegistrationUiEvents.OnSubmitButtonTap -> handleSubmitButtonClick(event.formData)
            }
        }
    }

    private suspend fun handleSubmitButtonClick(profileData: ProfileData) {
        val result = formDataValidate(profileData)
        if (result.overallOk) {
            uiEffects.emit(RegistrationUiEffects.Loading)
            CoroutineScope(Dispatchers.IO).launch {
                val result = async {
                    requestRegistration(profileData)
                }.await()

                CoroutineScope(Dispatchers.Main).launch {
                    when (result) {
                        is RegistrationStatus.Success -> uiEffects.emit(RegistrationUiEffects.RegistrationSuccessful)
                        is RegistrationStatus.Failure -> uiEffects.emit(
                            RegistrationUiEffects.Error(
                                result.errorMessage
                            )
                        )
                    }
                }
            }

        } else {
            uiEffects.emit(RegistrationUiEffects.FormError(result))
        }
    }
}