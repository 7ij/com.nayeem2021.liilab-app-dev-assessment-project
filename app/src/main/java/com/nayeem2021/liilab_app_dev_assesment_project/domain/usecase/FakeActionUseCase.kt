package com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase

import android.util.Log
import com.nayeem2021.liilab_app_dev_assesment_project.data.model.ActionResponse
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.SessionManagerImpl
import javax.inject.Inject

class FakeActionUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator suspend fun invoke(token: String) : ActionResponse<Any> {
        val actionResult = authRepository.performFakeAction(token)
        Log.i("debugTag", "actionResult: $actionResult")
        if (actionResult is ActionResponse.InvalidToken) {
            SessionManagerImpl.clearToken()
        }

        return actionResult
    }
}