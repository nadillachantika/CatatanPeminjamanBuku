package com.nadillla.catatanpinjambuku.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class,Catatan::class], version = 1)
abstract  class CatatanDatabase :RoomDatabase(){
    abstract fun userDao(): DaoUser
    abstract fun catatanDao(): DaoCatatan

    companion object{
        private var INSTANCE: CatatanDatabase? = null
        fun getInstance(context: Context):CatatanDatabase{
            synchronized(this){
                var instance: CatatanDatabase? = INSTANCE
                if(instance == null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        CatatanDatabase::class.java,
                        "catatanDb.db"
                    ).build()
                }
                return instance
            }
        }
    }
}