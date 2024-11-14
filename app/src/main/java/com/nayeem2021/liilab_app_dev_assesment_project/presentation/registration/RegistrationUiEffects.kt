package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationFormValidityStatus

open class RegistrationUiEffects {
    data object Loading : RegistrationUiEffects()
    data object RegistrationSuccessful : RegistrationUiEffects()
    data class FormError(val formError: RegistrationFormValidityStatus) : RegistrationUiEffects()
    data class Error(val message: String) : RegistrationUiEffects()

    data class EmailError(val msg: String) : RegistrationUiEffects()
    object EmailOk : RegistrationUiEffects()

    data class NameError(val msg: String) : RegistrationUiEffects()
    object NameOk : RegistrationUiEffects()

    data class PasswordError(val msg: String) : RegistrationUiEffects()
    object PasswordOk : RegistrationUiEffects()

    data class DobError(val msg: String) : RegistrationUiEffects()
    object DobOk : RegistrationUiEffects()
}
