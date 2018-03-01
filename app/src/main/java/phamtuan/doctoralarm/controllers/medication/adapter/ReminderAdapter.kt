package phamtuan.doctoralarm.controllers.medication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.medication.viewholder.ReminderViewholder
import phamtuan.doctoralarm.entity.Reminder

/**
 * Created by P.Tuan on 11/22/2017.
 */
class ReminderAdapter(context: Context, var listReminder: ArrayList<Reminder>, var listener: ReminderViewholder.Interactor) : RecyclerView.Adapter<ReminderViewholder>() {
    var inflater = LayoutInflater.from(context)
    override fun onBindViewHolder(holder: ReminderViewholder?, position: Int) {
        if (position == 0) holder?.cancelDelete(true)
        else holder?.cancelDelete(false)
        holder?.bindata(listReminder[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReminderViewholder {
        var view = inflater.inflate(R.layout.item_remember, parent, false)
        return ReminderViewholder(view, listener)
    }

    override fun getItemCount(): Int {
        return listReminder.size
    }
}