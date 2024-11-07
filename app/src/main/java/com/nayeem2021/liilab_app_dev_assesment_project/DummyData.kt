package com.nayeem2021.liilab_app_dev_assesment_project

object DummyData {
    fun getComments(): List<CommentModel> {
        return listOf(
            CommentModel(
                "Swapan Bala",
                "9h ago",
                "Looks amazing and breathtaking. Been there, beautiful!",
                listOf(
                    ReplyModel(
                        "Whitechapel Gallery",
                        "6h ago",
                        "Swapan Bala",
                        "Looks amazing and breathtaking. Been there, beautiful!",
                        "Thank you @Swapan Bala"
                    ),
                    ReplyModel(
                        "Md. Ashiqur Rahman Naeem",
                        "6h ago",
                        "Whitechapel Gallery",
                        "Thank you @Swapan Bala",
                        "No need to thak him. He is not good. @Whitechapel Gallery"
                    )
                )
            ),
            CommentModel(
                "Rahim Badsha",
                "1h ago",
                "Looks amazing and breathtaking. Been there, beautiful!",
                listOf(
                    ReplyModel(
                        "Whitechapel Gallery",
                        "6h ago",
                        "Swapan Bala",
                        "Looks amazing and breathtaking. Been there, beautiful!",
                        "Thank you @Swapan Bala"
                    ),
                    ReplyModel(
                        "Md. Ashiqur Rahman Naeem",
                        "6h ago",
                        "Whitechapel Gallery",
                        "Thank you @Swapan Bala",
                        "No need to thak him. He is not good. @Whitechapel Gallery"
                    )
                )
            )
        )
    }

    fun getPosts(): List<PostModel> {
        return listOf(
            PostModel(
                "Sepural Gallery",
                "15h.",
                R.drawable.dummy_post_user_photo_1,
                postPrivacy = "Public",
                postContent = "If you think adventure is dangerous, try routine, it’s lethal Paulo Coelho! Good morning all friends.",
                postImages = listOf(
                    R.drawable.dummy_post_uploaded_photo_1,
                ),
                comments = listOf<CommentModel>(),
                likesCount = 12,
                commentsCount = 3,
                sharesCount = 17
            ),
            PostModel(
                user = "Prothinidi Thomas",
                "2d.",
                userImageResourceId = R.drawable.dummy_post_user_photo_2,
                postPrivacy = "Public",
                postContent = "If you think adventure is dangerous, try routine, it’s lethal Paulo Coelho! Good morning all friends.",
                postImages = listOf(
                    R.drawable.dummy_post_uploaded_photo_4,
                    R.drawable.dummy_post_uploaded_photo_2,
                    R.drawable.dummy_post_uploaded_photo_3,
                    R.drawable.dummy_post_uploaded_photo_3,
                    R.drawable.dummy_post_uploaded_photo_3
                ),
                comments = getComments(),
                likesCount = 11,
                commentsCount = 4,
                sharesCount = 5
            )
        )
    }

    fun getRecentEvents(): HomePageRecentEventModel {
        val eventList = listOf(
            SingleRecentEventModel(
                "Graduation Ceremony",
                "The graduation ceremony is also sometimes called. The graduation " +
                        "ceremony is also sometimes called...",
                8
            ),
            SingleRecentEventModel(
                "Photography Ideas",
                "Reflections. Reflections work because they can create...",
                11
            )
        )
        return HomePageRecentEventModel(eventList)
    }
}
