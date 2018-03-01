package phamtuan.doctoralarm.controllers.medication.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import butterknife.BindView
import kotlinx.android.synthetic.main.item_spiner_default.view.*
import kotlinx.android.synthetic.main.item_view_drop.view.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.commonbase.SpinerAdapterCmm

/**
 * Created by P.Tuan on 10/13/2017.
 */
class InstructionAdapterSpinner(context: Context,var listdata:List<String>) : SpinerAdapterCmm(mContext = context,data = listdata) {
@BindView(R.id.tvValues) var tvValues:TextView? = null
    override fun initView(view: View, position: Int) {
        view.tvValuesDefault?.text = listdata[position]
    }

    override fun initViewDrop(view: View, position: Int) {
        view.tvValues?.text = listdata[position]
    }
}