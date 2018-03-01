package phamtuan.doctoralarm.controllers.doctor.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.entity.Doctor

/**
 * Created by P.Tuan on 10/26/2017.
 */
class DoctorsListAdapter(context: Context, var listDoctor: ArrayList<Doctor>,var listener: Interacter) : RecyclerView.Adapter<DoctorViewHolder>() {
    var layoutinflate = LayoutInflater.from(context)
    override fun getItemCount(): Int {
        return listDoctor.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DoctorViewHolder {
        var view = layoutinflate.inflate(R.layout.item_infomation_doctor, parent, false)
        return DoctorViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder?, position: Int) {
        holder?.bindata(listDoctor[position])
    }
    interface Interacter{
        fun OnNewMessage(index: Int)

        fun OnNewCall(index: Int)

        fun OnEdit(index: Int)
    }
}