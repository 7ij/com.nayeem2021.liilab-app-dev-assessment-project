package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(profileData: ProfileData) : String {
        val dataValidator = RegistrationDataValidationUseCase()
        val validationResult = dataValidator(profileData)
        return if(validationResult.overallOk)
            authRepository.register(profileData)
        else
            "input data is not in valid format"
    }
}
