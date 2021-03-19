package com.nadillla.catatanpinjambuku.Local

import androidx.room.*

@Dao
interface DaoCatatan {
    @Query(value = "SELECT * FROM catatan_data" )
    fun getDataCatatan():List<Catatan>

    @Query("SELECT COUNT(catatan_id) as jumlah  FROM catatan_data WHERE status='Belum Dikembalikan'")
    fun getTotal():String?

    @Insert
    fun insertCatatan(catatan: Catatan)

    @Update
    fun updateCatatan(catatan: Catatan?)

    @Delete
    fun deleteCatatan(catatan: Catatan?)
}