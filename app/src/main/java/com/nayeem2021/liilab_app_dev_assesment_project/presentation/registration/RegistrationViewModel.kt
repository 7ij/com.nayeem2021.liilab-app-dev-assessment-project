package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nayeem2021.liilab_app_dev_assesment_project.domain.model.RegistrationStatus
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.EmailValidityCheckUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.PasswordValidityCheckUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationDataValidationUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val requestRegistration: RegistrationUseCase,
    private val formDataValidate: RegistrationDataValidationUseCase
) : ViewModel() {

    private val _uiEffects = MutableSharedFlow<RegistrationUiEffects>()
    val uiEffects = _uiEffects

    @Inject
    lateinit var isValidEmail: EmailValidityCheckUseCase

    @Inject
    lateinit var isValidPassword: PasswordValidityCheckUseCase

    fun handleEvent(event: RegistrationUiEvents) {
        viewModelScope.launch {
            when (event) {
                is RegistrationUiEvents.OnSubmitButtonTap -> handleSubmitButtonClick(event.formData)
                is RegistrationUiEvents.onFinishedWritingName -> handleFinishedWritingName(event.name)
                is RegistrationUiEvents.OnFinishedWritingEmail -> handleFinishedWritingEmail(event.email)
                is RegistrationUiEvents.onFinishedWritingPassword -> handleFinishedWritingPassword(
                    event.password
                )

                is RegistrationUiEvents.onFinishedWritingDob -> handleFinishedWritingDob(event.dob)
            }
        }
    }

    private suspend fun handleFinishedWritingDob(dob: String) {
        if (dob.isEmpty()) {
            _uiEffects.emit(
                RegistrationUiEffects.DobError(
                    "Date of birth can not be empty"
                )
            )
        } else if (!isValidDate(dob)) {
            _uiEffects.emit(
                RegistrationUiEffects.DobError(
                    "valid format: dd/MM/yyyy"
                )
            )
        } else {
            _uiEffects.emit(
                RegistrationUiEffects.DobOk
            )
        }
    }

    private fun isValidDate(date: String, format: String = "dd/MM/yyyy"): Boolean {
        return try {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
            dateFormat.isLenient = false
            dateFormat.parse(date) != null
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun handleFinishedWritingPassword(password: String) {
        Log.i("lolita", "lolita: $password")
        isValidPassword(password)
        if (password.isEmpty()) {
            _uiEffects.emit(
                RegistrationUiEffects.PasswordError("Password can't be empty")
            )
        } else if (!isValidPassword(password)) {
            _uiEffects.emit(
                RegistrationUiEffects.PasswordError(
                    "At least one digit, one special character and 8 digits long"
                )
            )
        } else {
            _uiEffects.emit(RegistrationUiEffects.PasswordOk)
        }
    }

    private suspend fun handleFinishedWritingEmail(email: String) {
        if (email.isEmpty()) {
            _uiEffects.emit(
                RegistrationUiEffects.EmailError("Email cannot be empty")
            )
        } else if (!isValidEmail(email)) {
            _uiEffects.emit(
                RegistrationUiEffects.EmailError("Email address is not valid")
            )
        } else {
            _uiEffects.emit(RegistrationUiEffects.EmailOk)
        }
    }

    private suspend fun handleFinishedWritingName(name: String) {
        if (name.length < 3) {
            _uiEffects.emit(
                RegistrationUiEffects.NameError("Name can't be less than 3 characters")
            )
        } else {
            _uiEffects.emit(
                RegistrationUiEffects.NameOk
            )
        }
    }

    private suspend fun handleSubmitButtonClick(profileData: ProfileData) {
        val result = formDataValidate(profileData)
        if (true) {
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