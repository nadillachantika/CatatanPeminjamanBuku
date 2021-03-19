package com.nadillla.catatanpinjambuku.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nadillla.catatanpinjambuku.Local.Catatan
import com.nadillla.catatanpinjambuku.Repository.CatatanRepository

class CatatanViewModel (application: Application):AndroidViewModel(application){

    val repoCatt = CatatanRepository(application.applicationContext)
    var responseDataCatt = MutableLiveData<List<Catatan>>()
    var _responseData:LiveData<List<Catatan>> = responseDataCatt

    var responseActionCatt = MutableLiveData<Catatan>()
    var _responseActionCatt: LiveData<Catatan> = responseActionCatt

    var isError = MutableLiveData<Throwable>()
    var _isError :LiveData<Throwable> = isError

    fun getListCatatan(){
        repoCatt.showCatatan({
            responseDataCatt.value=it
        },{

            isError.value=it
        })
    }
    fun addCatatan(id:Int?,tglPinjam:String,tglKembali:String,nama:String,nohp:String,isbn:String,judul:String,penulis:String,status:String){

        repoCatt.addCatatan(id,tglPinjam,tglKembali,nama,nohp,isbn,judul,penulis,status,{
            responseActionCatt.value=it
        },{
            isError.value=it
        })
    }

    fun updateCatatan(id:Int?,tglPinjam:String,tglKembali:String,nama:String,nohp:String,isbn:String,judul:String,penulis:String,status:String) {

        repoCatt.updateCatatan(id, tglPinjam, tglKembali, nama, nohp, isbn, judul, penulis, status, {
            responseActionCatt.value = it
        }, {
            isError.value = it
        })
    }

    fun deleteCatatan(item:Catatan){
        repoCatt.deleteCatatan(item,{
            responseActionCatt.value=it
        },{
            isError.value=it
        })
    }
}