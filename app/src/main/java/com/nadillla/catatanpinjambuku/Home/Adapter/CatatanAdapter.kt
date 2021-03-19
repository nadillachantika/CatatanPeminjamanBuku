package com.nadillla.catatanpinjambuku.Home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nadillla.catatanpinjambuku.Local.Catatan
import com.nadillla.catatanpinjambuku.R
import kotlinx.android.synthetic.main.item_catatan.view.*

class CatatanAdapter(

    private val data: List<Catatan>?,
    private val itemClick: OnClickListener)
    :RecyclerView.Adapter<CatatanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatanAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catatan,parent,false)

        return ViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int = data?.size?:0

    override fun onBindViewHolder(holder: CatatanAdapter.ViewHolder, position: Int) {

        val item : Catatan? = data?.get(position)
        holder.bind(item)
    }

    class ViewHolder(val view: View,val itemClick: OnClickListener): RecyclerView.ViewHolder(view){

        fun bind(item: Catatan?){
            view.edNama.text = item?.nama
            view.edTglKembali.text=item?.tglkembali
            view.edStatus.text=item?.status
            view.edJudul.text=item?.judul

            view.btnEdit.setOnClickListener {
                itemClick.update(item)
            }
            view.btnDelete.setOnClickListener {
                itemClick.delete(item)
            }

        }
    }

    interface OnClickListener{
        fun update(item: Catatan?)
        fun delete(item: Catatan?)
    }




}




