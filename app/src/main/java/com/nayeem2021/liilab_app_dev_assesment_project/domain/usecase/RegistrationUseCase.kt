package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import com.nayeem2021.liilab_app_dev_assesment_project.domain.model.RegistrationStatus
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator suspend fun invoke(profileData: ProfileData) : RegistrationStatus {
        val status = authRepository.register(profileData)
        return status
    }
}
