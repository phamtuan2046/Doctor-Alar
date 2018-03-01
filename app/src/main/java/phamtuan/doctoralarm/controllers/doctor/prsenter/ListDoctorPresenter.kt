package phamtuan.doctoralarm.controllers.doctor.prsenter

import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.controllers.doctor.fragment.FragmentListDoctor
import phamtuan.doctoralarm.controllers.doctor.interactor.DoctorListListener
import phamtuan.doctoralarm.entity.Doctor

/**
 * Created by P.Tuan on 10/26/2017.
 */
class ListDoctorPresenter(var view: FragmentListDoctor, var listener: DoctorListListener) : BasePresenter() {

    fun getListDoctor() {
        var mRealm = realmHelper.getRealm()
        var listDoctor = realmHelper.findAll(mRealm,Doctor::class.java)
        mRealm.close()
        if (listDoctor != null && listDoctor.isNotEmpty()) {
            listener.OnLoadDoctorSuccess(listDoctor)
        }
    }
}