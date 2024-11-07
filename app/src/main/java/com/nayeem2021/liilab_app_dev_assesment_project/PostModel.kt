package com.nayeem2021.liilab_app_dev_assesment_project

data class PostModel(
    val user: String,
    val postTime: String,
    val userImageResourceId: Int,
    val likesCount: Int,
    val commentsCount: Int,
    val sharesCount: Int,
    val postPrivacy: String,
    val postContent: String,
    val postImages: List<Int>,
    val comments: List<CommentModel>,
)
