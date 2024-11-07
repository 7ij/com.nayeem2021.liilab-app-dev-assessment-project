package com.nayeem2021.liilab_app_dev_assesment_project.model

import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationFormValidityStatus

sealed class RegistrationFormStatus {
    data object Successful : RegistrationFormStatus()
    data class FormValidationFailure(val registrationFormValidityStatus: RegistrationFormValidityStatus) :
        RegistrationFormStatus()
    data class OtherError(val message: String) : RegistrationFormStatus()
}