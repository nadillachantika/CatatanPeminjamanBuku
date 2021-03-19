package com.nadillla.catatanpinjambuku.Home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nadillla.catatanpinjambuku.Local.CatatanDatabase
import com.nadillla.catatanpinjambuku.R
import com.nadillla.catatanpinjambuku.ViewModel.UserViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_beranda.*
import java.util.*


class BerandaFragment : Fragment() {

    private var catatanDB : CatatanDatabase? = null
    private lateinit var userViewModel: UserViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catatanDB = context?.let { CatatanDatabase.getInstance(it) }
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showJmlPeminjaman()
    }

    private fun showJmlPeminjaman() {
        Observable.fromCallable { catatanDB?.catatanDao()?.getTotal() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tvJml.setText("$it")
                Log.d(TAG, "showJmlPeminjaman: $it")},
                {
                Log.d(TAG, "showJmlPeminjaman: gagal, ${it.message}")
            })
    }


}