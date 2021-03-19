package com.nadillla.catatanpinjambuku.Local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "catatan_data")
data class Catatan (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "catatan_id")
    val id : Int? = null,

    @ColumnInfo(name = "tgl_pinjam")
    val tglpinjam: String?,

    @ColumnInfo(name = "tgl_kembali")
    val tglkembali: String?,

    @ColumnInfo(name= "nama")
    val nama: String?,

    @ColumnInfo(name = "no_hp")
    val nohp: String?,

    @ColumnInfo(name = "isbn")
    val isbn : String?,

    @ColumnInfo(name = "judul")
    val judul: String?,

    @ColumnInfo(name = "penulis")
    val penulis: String?,

    @ColumnInfo(name ="status")
    val status: String?

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    val gambar: Byte


)