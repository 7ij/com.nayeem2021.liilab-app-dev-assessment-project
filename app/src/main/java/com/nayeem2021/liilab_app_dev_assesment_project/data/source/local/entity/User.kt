package com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "password") val password: String?
)