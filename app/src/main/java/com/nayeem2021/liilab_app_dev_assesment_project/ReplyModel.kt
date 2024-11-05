package com.nayeem2021.liilab_app_dev_assesment_project

data class ReplyModel(
    val user: String,
    val whenReplied: String,
    val replyingTo: String,
    val replyingToContent: String,
    val replyMsg: String
)
