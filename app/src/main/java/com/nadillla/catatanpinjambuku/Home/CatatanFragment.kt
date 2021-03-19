package com.nadillla.catatanpinjambuku.Home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nadillla.catatanpinjambuku.Home.Adapter.CatatanAdapter
import com.nadillla.catatanpinjambuku.Local.Catatan
import com.nadillla.catatanpinjambuku.Local.CatatanDatabase
import com.nadillla.catatanpinjambuku.Local.DaoCatatan
import com.nadillla.catatanpinjambuku.R
import com.nadillla.catatanpinjambuku.ViewModel.CatatanViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_add.*
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.fragment_catatan.*
import java.text.SimpleDateFormat
import java.util.*


class CatatanFragment : Fragment() {
    private var dialogView: Dialog? = null
    private lateinit var catatanViewModel: CatatanViewModel
    private var catatandb: CatatanDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catatan, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catatanViewModel = ViewModelProvider(this).get(CatatanViewModel::class.java)
        catatandb = context?.let { CatatanDatabase.getInstance(it) }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catatanViewModel.getListCatatan()
        attachObserver()

        fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_add, null)
        dialog.setView(view)

        view.edtglPinjam.setText(getDate())
        view.edTglKembali.setText(getDate())
        view.btnSimpan.setOnClickListener {

//            if (view.edNama.text.toString().isEmpty() || view.edJudul.text.toString().isEmpty() && view.edPenulis.text.toString()
//                    .isEmpty() && view.edIsbn.text.isEmpty()
//            ) {
//                Toast.makeText(context, "Nama, Judul Buku, Nama Penulis, dan ISBN tidak boleh kosong", Toast.LENGTH_SHORT).show()
//            } else {

            catatanViewModel.addCatatan(
                null,
                view.edtglPinjam.text.toString(),
                view.edTglKembali.text.toString(),
                view.edNama.text.toString(),
                view.edHp.text.toString(),
                view.edIsbn.text.toString()
                ,
                view.edJudul.text.toString(),
                view.edPenulis.text.toString(),
                view.edStatus.selectedItem.toString()
            )
        }


            view.btnClose.setOnClickListener {
                dialogView?.dismiss()
            }
            dialogView = dialog.create()
            dialogView?.show()
        }


//    private fun insertCatatan(item: Catatan) {
//        Observable.fromCallable { catatandb?.catatanDao()?.insertCatatan(item) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Toast.makeText(context, "catatan berhasil disimpan", Toast.LENGTH_SHORT).show()
//            },{
//                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//            })
//
//    }

    private fun attachObserver() {
        //get data
        catatanViewModel._responseData.observe(
            viewLifecycleOwner, Observer { showTabungan(it) }

        )
        catatanViewModel._isError.observe(
            viewLifecycleOwner, Observer { errorGetData(it) })


        // input data
        catatanViewModel._responseActionCatt.observe(
            viewLifecycleOwner, Observer { inputTabungan(it) }
        )
        catatanViewModel._isError.observe(
            viewLifecycleOwner, Observer { inputTabunganError(it) }
        )


    }

    private fun inputTabunganError(it: Throwable?) {
        Log.d("TAG", "inputTabunganError: ${it?.message}")

    }

    private fun errorGetData(it: Throwable?) {
        Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show()

    }

    private fun inputTabungan(it: Catatan?) {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        Log.d("TAG", "inputTabungan: Gagal")
        dialogView?.dismiss()
        catatanViewModel.getListCatatan()

    }

    private fun showTabungan(item: List<Catatan>?) {
        listCatt.adapter =
            CatatanAdapter(item, object : CatatanAdapter.OnClickListener {
                override fun update(item: Catatan?) {

                    showUpdateCatatan(item)
                }

                override fun delete(item: Catatan?) {
                    AlertDialog.Builder(context).apply {
                        setTitle("Hapus")
                        setMessage("Yakin hapus data ?")
                        setCancelable(false)

                        setPositiveButton("Yakin") { dialogInterface, i ->
                            if (item != null) {
                                catatanViewModel.deleteCatatan(item)
                            }
                        }
                        setNegativeButton("Batal") { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                    }.show()
                }

            })

    }

    private fun showUpdateCatatan(item: Catatan?) {

        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_add, null)
        dialog.setView(view)

        view.btnSimpan.text = "Update"
        view.edNama.setText((item?.nama))
        view.edHp.setText((item?.nohp))
        view.edtglPinjam.setText((item?.tglpinjam))
        view.edTglKembali.setText((item?.tglkembali))
        view.edIsbn.setText(item?.isbn)
        view.edPenulis.setText(item?.penulis)
        view.edJudul.setText(item?.judul)
//        view.edStatus.setSelection(item?.status)


        view.btnSimpan.setOnClickListener {

//            if (view.edNama.text.toString().isEmpty() && view.edHp.text.toString()
//                    .isEmpty() && view.edtglPinjam.text.toString()
//                    .isEmpty() && view.edTglKembali.text.toString().isEmpty()
//                && view.edJudul.text.toString().isEmpty() && view.edPenulis.text.toString()
//                    .isEmpty() && view.edIsbn.text.isEmpty()
//            ) {
//                Toast.makeText(context, "Tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
//            } else {

                catatanViewModel.updateCatatan(
                    item?.id,
                    view.edtglPinjam.text.toString(),
                    view.edTglKembali.text.toString(),
                    view.edNama.text.toString(),
                    view.edHp.text.toString(),
                    view.edIsbn.text.toString(),
                    view.edJudul.text.toString(),
                    view.edPenulis.text.toString(),
                    view.edStatus.selectedItem.toString()
                )
//                dialogView?.dismiss()

//            }
        }

        view.btnClose.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()
    }


    private fun getDate(): String {
        val date = Calendar.getInstance().time
        val format = SimpleDateFormat.getDateInstance()
        val formatDate = format.format(date)
        return formatDate

    }
}



