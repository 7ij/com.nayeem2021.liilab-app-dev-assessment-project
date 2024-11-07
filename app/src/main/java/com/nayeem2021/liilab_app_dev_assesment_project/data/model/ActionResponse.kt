package com.nayeem2021.liilab_app_dev_assesment_project.data.model

sealed class ActionResponse<out T>{
    data class Success<out T>(val data: T): ActionResponse<T>()
    data class InvalidToken(val error: String): ActionResponse<Nothing>()
    data class Error(val error: String): ActionResponse<Nothing>()
}
