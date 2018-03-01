package phamtuan.doctoralarm.controllers.doctor.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import kotlinx.android.synthetic.main.item_infomation_doctor.view.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.R.id.btnMessage
import phamtuan.doctoralarm.entity.Doctor

/**
 * Created by P.Tuan on 10/26/2017.
 */
class DoctorViewHolder(itemview: View, var listener: DoctorsListAdapter.Interacter) : RecyclerView.ViewHolder(itemview) {
    init {
        itemview?.btnMessage.setOnClickListener {
            listener.OnNewMessage(adapterPosition)
        }
        itemview?.btnCall.setOnClickListener {
            listener.OnNewCall(adapterPosition)
        }
        itemview?.btnEdit.setOnClickListener {
            listener.OnEdit(adapterPosition)
        }
    }

    fun bindata(doctor: Doctor) {
        itemView.tvDoctorName.text = doctor.doctor_name
        if (!doctor.specialization.isNullOrEmpty())
            itemView.tvSpecialzation.text = doctor.specialization
        if (!doctor.doctor_phone.isNullOrEmpty())
            itemView.tvPhone.text = doctor.doctor_phone
    }
}