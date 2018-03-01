package phamtuan.doctoralarm.controllers.daily.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_signgleintake_daily.view.*
import phamtuan.doctoralarm.controllers.daily.adapter.DailyItemAdapter
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.utils.DateUtil

/**
 * Created by P.Tuan on 11/20/2017.
 */
class SingleIntakeViewholder(itemview: View, listener: DailyItemAdapter.Interactor) : RecyclerView.ViewHolder(itemview) {
    init {
        itemview.setOnClickListener {
            listener.OnSingleIntakeClick(adapterPosition)
        }
    }

    fun bindata(obj: Medication) {
        itemView?.tvDate?.text = DateUtil.converDateToString(obj.start_date!!, DateUtil.DATE_FORMAT_3)
        itemView?.tvName?.text = obj.medication_name
        itemView?.tvContent?.text = obj.values_unit + " " + obj.unit
    }
}