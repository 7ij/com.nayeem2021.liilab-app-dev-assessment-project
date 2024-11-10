package com.nayeem2021.liilab_app_dev_assesment_project.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.dao.UserDao
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun userDao() : UserDao
}