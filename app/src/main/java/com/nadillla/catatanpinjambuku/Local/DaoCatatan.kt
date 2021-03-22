package com.nadillla.catatanpinjambuku.Local

import androidx.room.*

@Dao
interface DaoCatatan {
    @Query(value = "SELECT * FROM catatan_data" )
    fun getDataCatatan():List<Catatan>

    @Query("SELECT COUNT(catatan_id) as jumlah  FROM catatan_data WHERE status='Belum Dikembalikan'")
    fun getTotal():String?

    @Query("SELECT COUNT(catatan_id) as jumlah  FROM catatan_data WHERE status='Sudah Dikembalikan'")
    fun getTotalKembali():String?


//    @Query("SELECT COUNT(tgl_kembali) as jumlah from catatan_data WHERE tgl_kembali =  DATE_ADD(CURDATE(), INTERVAL 1 DAY) as besok;")
//    fun getTomorrow():String?

    @Insert
    fun insertCatatan(catatan: Catatan)

    @Update
    fun updateCatatan(catatan: Catatan?)

    @Delete
    fun deleteCatatan(catatan: Catatan?)
}