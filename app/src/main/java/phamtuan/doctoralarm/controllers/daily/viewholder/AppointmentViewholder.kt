package phamtuan.doctoralarm.controllers.daily.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_appointment_daily.view.*
import phamtuan.doctoralarm.controllers.daily.adapter.DailyItemAdapter
import phamtuan.doctoralarm.entity.Appointment
import phamtuan.doctoralarm.utils.DateUtil

/**
 * Created by P.Tuan on 11/20/2017.
 */
class AppointmentViewholder(itemview: View,listener: DailyItemAdapter.Interactor?) : RecyclerView.ViewHolder(itemview) {
    init {
        itemview.setOnClickListener {
            listener?.OnAppointmentclick(adapterPosition)
        }
    }

    fun bindata(appointment: Appointment) {
        itemView?.tvDate?.text = DateUtil.converDateToString(appointment.appoinmentDate!!, DateUtil.DATE_FORMAT_3)
        itemView?.tvName?.text = "Appointment"
        itemView?.tvContent?.text = appointment.doctor?.doctor_name
    }
}