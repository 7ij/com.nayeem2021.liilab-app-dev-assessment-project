package com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<User>

    @Insert
    suspend fun insertUser(users: User) : Long

    @Delete
    suspend fun deleteUser(user: User)
}