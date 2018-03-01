package phamtuan.doctoralarm.controllers.commonbase

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.item_spiner_default.view.*
import kotlinx.android.synthetic.main.item_view_drop.view.*
import phamtuan.doctoralarm.entity.Doctor

/**
 * Created by P.Tuan on 10/23/2017.
 */
class DoctorAdapter(context: Context,var listDoctor: ArrayList<Doctor>): SpinerAdapterCmm(context,listDoctor) {
    override fun initView(view: View, position: Int) {
        view.tvValuesDefault?.text = listDoctor[position].doctor_name
    }

    override fun initViewDrop(view: View, position: Int) {
        view.tvValues?.text = listDoctor[position].doctor_name
    }
}