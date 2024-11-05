package com.nayeem2021.liilab_app_dev_assesment_project

data class CommentModel(
    val user: String,
    val whenPosted: String,
    val content: String,
    val replies: List<ReplyModel>
)