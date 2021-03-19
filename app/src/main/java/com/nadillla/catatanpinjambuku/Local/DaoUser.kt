package com.nadillla.catatanpinjambuku.Local

import androidx.room.*

@Dao
interface DaoUser {

    @Query("SELECT * FROM user_data WHERE user_email=:email AND user_password=:password LIMIT 1")
    fun login(email: String?, password:String?):User

    @Query("SELECT * FROM user_data WHERE user_email=:email LIMIT 1")
    fun getUser(email: String?):User

    @Insert
    fun register(user: User)
}