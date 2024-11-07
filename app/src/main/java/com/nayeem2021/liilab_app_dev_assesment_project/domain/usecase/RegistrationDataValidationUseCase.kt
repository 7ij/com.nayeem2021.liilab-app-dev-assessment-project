package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import android.util.Log
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

class RegistrationDataValidationUseCase {
    operator fun invoke(profileData: ProfileData): RegistrationFormValidityStatus {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        val passwordRegex =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#%$^&*])[A-Za-z\\d@#%$^&*]{8,64}$"

        val emailOk = emailRegex.toRegex().matches(profileData.username)
        val passwordOk = passwordRegex.toRegex().matches(profileData.password)
        val nameOk = profileData.name.length >= 3
        val dateOfBirthOk = true // for now let's assume all dob is ok
        Log.i(
            "lolita", "emailOk: $emailOk, passwordOk: $passwordOk," +
                    "nameOk: $nameOk, dateOfBirthOk: $dateOfBirthOk"
        )
        val overallOk = emailOk && passwordOk && nameOk && dateOfBirthOk
        return RegistrationFormValidityStatus(
            overallOk,
            emailOk,
            passwordOk,
            nameOk,
            dateOfBirthOk
        )
    }
}

data class RegistrationFormValidityStatus(
    val overallOk: Boolean,
    val emailOk: Boolean,
    val passwordOk: Boolean,
    val nameOk: Boolean,
    val dateOfBirthOk: Boolean
)