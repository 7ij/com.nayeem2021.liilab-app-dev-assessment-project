package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import android.util.Log
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

class EmailValidityCheckUseCase {
    operator fun invoke(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}