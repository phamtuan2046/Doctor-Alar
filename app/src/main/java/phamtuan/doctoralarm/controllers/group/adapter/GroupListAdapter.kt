package phamtuan.doctoralarm.controllers.group.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_group.view.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.entity.Group

/**
 * Created by P.Tuan on 11/28/2017.
 */
class GroupListAdapter(context: Context, var groups: ArrayList<Group>, var listener: Interactor) : RecyclerView.Adapter<GroupListAdapter.GroupHolder>() {
    var inflater: LayoutInflater? = null

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: GroupHolder?, position: Int) {
        holder?.bindata(groups[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GroupHolder {
        var view = inflater!!.inflate(R.layout.item_group, parent, false)
        return GroupHolder(view, listener)
    }

    interface Interactor {
        fun OnEdit(index: Int)
    }

    class GroupHolder(var itemview: View, var listener: Interactor) : RecyclerView.ViewHolder(itemview) {
        init {
            itemview.btnEditGroup.setOnClickListener {
                listener.OnEdit(adapterPosition)
            }
        }
        fun bindata(group:Group){
            itemview.tvNameGroup.text = group.group_name
        }
    }
}