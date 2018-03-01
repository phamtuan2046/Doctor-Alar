package phamtuan.doctoralarm.controllers.medication.adapter

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.item_spiner_default.view.*
import kotlinx.android.synthetic.main.item_view_drop.view.*
import phamtuan.doctoralarm.controllers.commonbase.SpinerAdapterCmm
import phamtuan.doctoralarm.entity.Group

/**
 * Created by P.Tuan on 10/13/2017.
 */
class GroupAdapterSpinner(context: Context, var groups: ArrayList<Group>) : SpinerAdapterCmm(context, groups) {
    override fun initView(view: View, position: Int) {
        view.tvValuesDefault?.text = groups[position].group_name
    }

    override fun initViewDrop(view: View, position: Int) {
        view.tvValues?.text = groups[position].group_name
    }
}