package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import android.util.Log
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

class EmailValidityCheckUseCase {
    operator fun invoke(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        val emailOk = emailRegex.toRegex().matches(email)
        return emailOk
    }
}