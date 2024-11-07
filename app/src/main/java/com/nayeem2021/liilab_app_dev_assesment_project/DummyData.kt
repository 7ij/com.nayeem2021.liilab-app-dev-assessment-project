package com.nayeem2021.liilab_app_dev_assesment_project

object DummyData {
    fun getComments() : List<CommentModel> {
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
        return  listOf(
            PostModel(
                "Sepural Gallery",
                "15h.",
                R.drawable.dummy_post_user_photo_1,
                "Public",
                "If you think adventure is dangerous, try routine, it’s lethal Paulo Coelho! Good morning all friends.",
                listOf(
                    R.drawable.dummy_post_uploaded_photo_1,
                ),
                listOf<CommentModel>()
            ),
            PostModel(
                "Prothinidi Thomas",
                "2d.",
                R.drawable.dummy_post_user_photo_2,
                "Public",
                "If you think adventure is dangerous, try routine, it’s lethal Paulo Coelho! Good morning all friends.",
                listOf(
                    R.drawable.dummy_post_uploaded_photo_4,
                    R.drawable.dummy_post_uploaded_photo_2,
                    R.drawable.dummy_post_uploaded_photo_3
                ),
                getComments()
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
