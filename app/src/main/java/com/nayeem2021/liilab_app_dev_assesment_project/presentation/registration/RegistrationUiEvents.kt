package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

open class RegistrationUiEvents {
    data class OnSubmitButtonTap(val formData: ProfileData) : RegistrationUiEvents()
    // we can do more like OnFinishedWritingEmail to check if the email address already exist
    // like this, for each form data, we can set trigger and then check validation while the user
    // fills the form
    // for now let's just handle through submitButtonClick
    data class OnFinishedWritingEmail(val email: String) : RegistrationUiEvents()
    data class onFinishedWritingPassword(val password: String): RegistrationUiEvents()
    data class onFinishedWritingDob(val dob: String): RegistrationUiEvents()
    data class onFinishedWritingName(val name: String): RegistrationUiEvents()
}