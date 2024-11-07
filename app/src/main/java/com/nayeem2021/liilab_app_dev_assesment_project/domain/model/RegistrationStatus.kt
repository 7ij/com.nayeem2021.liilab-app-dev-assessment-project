package com.nayeem2021.liilab_app_dev_assesment_project.domain.model

open class RegistrationStatus {
    data object Success : RegistrationStatus()
    data class Failure(val errorMessage : String) : RegistrationStatus()
}
