package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

class PasswordValidityCheckUseCase {
    operator fun invoke(password: String): Boolean {
        val passwordRegex =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#%$^&*])[A-Za-z\\d@#%$^&*]{8,64}$"
        val passwordOk = passwordRegex.toRegex().matches(password)
        return passwordOk

    }
}
