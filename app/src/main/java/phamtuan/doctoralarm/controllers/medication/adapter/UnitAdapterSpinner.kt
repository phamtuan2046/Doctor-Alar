package phamtuan.doctoralarm.controllers.medication.adapter

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.item_spiner_default.view.*
import kotlinx.android.synthetic.main.item_view_drop.view.*
import phamtuan.doctoralarm.controllers.commonbase.SpinerAdapterCmm

/**
 * Created by P.Tuan on 10/13/2017.
 */
class UnitAdapterSpinner(context: Context, var listdata: List<String>) : SpinerAdapterCmm(mContext = context, data = listdata) {
    override fun initView(view: View, position: Int) {
        view.tvValuesDefault?.text = listdata[position]
    }

    override fun initViewDrop(view: View, position: Int) {
        view.tvValues?.text = listdata[position]
    }
}