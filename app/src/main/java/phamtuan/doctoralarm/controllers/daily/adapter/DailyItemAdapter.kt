package phamtuan.doctoralarm.controllers.daily.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.daily.viewholder.AppointmentViewholder
import phamtuan.doctoralarm.controllers.daily.viewholder.MedicationViewHolder
import phamtuan.doctoralarm.controllers.daily.viewholder.SingleIntakeViewholder
import phamtuan.doctoralarm.entity.Appointment
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.entity.ViewModel

/**
 * Created by P.Tuan on 11/20/2017.
 */
class DailyItemAdapter(var context: Context, var listObject: ArrayList<ViewModel>,var listener:Interactor) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val APPOINTMENT = 0
        val MEDICATION = 1
        val SINGLEINTAKE = 2
    }

    var inflater: LayoutInflater? = null

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            APPOINTMENT -> {
                var view = inflater?.inflate(R.layout.item_appointment_daily,parent,false)
                return AppointmentViewholder(view!!,listener)
            }
            MEDICATION -> {
                var view = inflater?.inflate(R.layout.item_medication_daily,parent,false)
                return MedicationViewHolder(view!!,listener)
            }
            else -> {
                var view = inflater?.inflate(R.layout.item_signgleintake_daily,parent,false)
                return SingleIntakeViewholder(view!!,listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when(holder){
            is AppointmentViewholder -> {
                holder.bindata(listObject[position] as Appointment)
            }
            is MedicationViewHolder -> {
                holder.bindata(listObject[position] as Medication)
            }
            is SingleIntakeViewholder -> {
                holder.bindata(listObject[position] as Medication)
            }
        }
    }

    override fun getItemCount(): Int {
        return listObject.size
    }

    override fun getItemViewType(position: Int): Int {
        when (listObject[position].keyItem()) {
            Constant.APPOIMENT_KEY -> {
                return APPOINTMENT
            }
            Constant.MEDICATION_KEY -> {
                return MEDICATION
            }
            else -> {
                return SINGLEINTAKE
            }
        }
    }

    interface Interactor{
        fun OnMedicaionClick(index: Int)

        fun OnSingleIntakeClick(index: Int)

        fun OnAppointmentclick(index: Int)
    }
}