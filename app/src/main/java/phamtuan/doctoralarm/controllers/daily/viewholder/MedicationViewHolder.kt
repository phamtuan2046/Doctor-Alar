package phamtuan.doctoralarm.controllers.daily.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_medication_daily.view.*
import phamtuan.doctoralarm.controllers.daily.adapter.DailyItemAdapter
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.utils.DateUtil

/**
 * Created by P.Tuan on 11/20/2017.
 */
class MedicationViewHolder(var itemview: View,var listener: DailyItemAdapter.Interactor): RecyclerView.ViewHolder(itemview) {
    init {
        itemview?.setOnClickListener {
            listener.OnMedicaionClick(adapterPosition)
        }
    }
    fun bindata(medication: Medication){
        itemview.tvTime.text =DateUtil.converDateToString(medication.start_date!!,DateUtil.DATE_FORMAT_3)
        itemview.tvName.text = medication.medication_name
        itemview.tvUnit.text = medication.values_unit + " " + medication.unit
    }
}