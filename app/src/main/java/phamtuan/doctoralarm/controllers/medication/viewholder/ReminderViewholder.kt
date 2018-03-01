package phamtuan.doctoralarm.controllers.medication.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_remember.view.*
import phamtuan.doctoralarm.entity.Reminder
import phamtuan.doctoralarm.utils.DateUtil

/**
 * Created by P.Tuan on 11/22/2017.
 */
class ReminderViewholder(var itemview: View, var listener: Interactor?) : RecyclerView.ViewHolder(itemview) {
    init {
        itemview?.btnGetTime.setOnClickListener {
            listener?.OnGetTime(adapterPosition)
        }
        itemview?.btnEdit.setOnClickListener {
            listener?.OnEdit(adapterPosition)
        }
        itemview?.btnDelete.setOnClickListener {
            listener?.OnDelete(adapterPosition)
        }
    }

    fun cancelDelete(cancel: Boolean) {
        if (cancel) {
            itemview.btnDelete.visibility = View.INVISIBLE
        } else {
            itemview.btnDelete.visibility = View.VISIBLE
        }

    }

    fun bindata(reminder: Reminder) {
        itemview?.tvTime.text = DateUtil.converDateToString(reminder.time!!, DateUtil.DATE_FORMAT_3)
        itemview?.tvUnit.text = reminder.values + " " + reminder.unit
    }

    interface Interactor {
        fun OnDelete(index: Int)

        fun OnGetTime(index: Int)

        fun OnEdit(index: Int)
    }
}