package phamtuan.doctoralarm.controllers.daily

import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.controllers.daily.fragment.DailyFragmentContent
import phamtuan.doctoralarm.entity.Appointment
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.entity.ViewModel
import java.util.*

/**
 * Created by P.Tuan on 11/20/2017.
 */
class DailyContentPresenter(var view: DailyFragmentContent, var listener: DailyContentListener) : BasePresenter() {

    fun getAlldata() {
        var listData = ArrayList<ViewModel>()
        var mRealm = realmHelper.getRealm()
        var listAppointment = realmHelper.findAll(mRealm, Appointment::class.java)
        if (listAppointment != null && listAppointment.isNotEmpty()) {
            listData.addAll(listAppointment)
        }
        var listMedication = realmHelper.findAll(mRealm, Medication::class.java)
        if (listMedication != null && listMedication.isNotEmpty()) {
            listData.addAll(listMedication)
        }
        Collections.sort(listData, kotlin.Comparator { t1, t2 ->
            return@Comparator t1.getDate()!!.compareTo(t2.getDate())
        })
        listener.OnLoadDataSuccess(listData)
    }
}