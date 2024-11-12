package com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = false) val uid : String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "dob") val dob: String,
)
