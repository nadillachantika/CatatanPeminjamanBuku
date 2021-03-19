package com.nadillla.catatanpinjambuku.Repository

import android.content.Context
import com.nadillla.catatanpinjambuku.Local.Catatan
import com.nadillla.catatanpinjambuku.Local.CatatanDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class CatatanRepository(val context: Context) {

    private var catatanDb = CatatanDatabase.getInstance(context)

    fun showCatatan(responseHandler: (List<Catatan>) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { catatanDb.catatanDao().getDataCatatan() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                responseHandler(item)
            }, {
                errorHandler(it)
            })

    }

    fun addCatatan(
        id: Int?, tglPinjam: String,
        tglKembali: String, nama: String,
        nohp: String, isbn: String, judul: String,
        penulis: String, status: String,
        responseHandler: (item: Catatan) -> Unit,
                errorHandler: (Throwable) -> Unit) {

        Observable.fromCallable {
            catatanDb.catatanDao().insertCatatan(
                Catatan(
                    id, tglPinjam, tglKembali, nama, nohp, isbn, judul, penulis, status)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item-> responseHandler(
                    Catatan(id,tglPinjam, tglKembali, nama, nohp,isbn, judul, penulis, status))
            }, {
                    errorHandler(it)
                })
    }
    fun updateCatatan(
        id: Int?, tglPinjam: String,
        tglKembali: String, nama: String,
        nohp: String, isbn: String, judul: String,
        penulis: String, status: String,
        responseHandler: (item: Catatan) -> Unit,
        errorHandler: (Throwable) -> Unit) {

        Observable.fromCallable {
            catatanDb.catatanDao().updateCatatan(
                Catatan(
                    id, tglPinjam, tglKembali, nama, nohp, isbn, judul, penulis, status
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item->
                responseHandler(
                    Catatan(id, tglPinjam, tglKembali, nama, nohp, isbn, judul, penulis, status)
                )
            }, {
                errorHandler(it)
            })
    }

    fun deleteCatatan(item:Catatan,responseHandler: (item: Catatan) -> Unit,errorHandler: (Throwable) -> Unit){
        Observable.fromCallable {
          catatanDb.catatanDao().deleteCatatan(item)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(item)
            },{
                errorHandler(it)
            })
    }
}