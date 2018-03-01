package phamtuan.doctoralarm.controllers.doctor.prsenter

import android.content.Intent
import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.doctor.CreateDoctorActivity
import phamtuan.doctoralarm.controllers.doctor.fragment.FragmentListDoctor
import phamtuan.doctoralarm.controllers.doctor.interactor.OnCreateDoctorView
import phamtuan.doctoralarm.controllers.main.MainActivity
import phamtuan.doctoralarm.controllers.medication.CreateMedicationActivity
import phamtuan.doctoralarm.controllers.singleintake.CreateSingleIntakeActivity
import phamtuan.doctoralarm.entity.Doctor

/**
 * Created by P.Tuan on 10/18/2017.
 */
class DoctorPresenter(var view: CreateDoctorActivity, var listener: OnCreateDoctorView) : BasePresenter() {

    private var FLAG: String? = null

    fun addDoctor(doctor: Doctor, flag: String?) {
        var mRealm = realmHelper.getRealm()
        when (FLAG) {
            CreateDoctorActivity.FLAG_EDIT -> {
                realmHelper.addOrUpdateObj(mRealm, doctor)
                mRealm.close()
                var result = Intent()
                result.putExtra(FragmentListDoctor.RESULT_DOCTOR, doctor)
                view.setResult(CreateDoctorActivity.RESULT_EDIT_DOCTOR, result)
                view.finish()
            }
            else -> {
                if (realmHelper.checkDoctorExit(mRealm, doctor)) {
                    listener.OnEmailExit()
                    mRealm.close()
                } else {
                    realmHelper.addOrUpdateObj(mRealm, doctor)
                    mRealm.close()
                    when (FLAG) {
                        CreateDoctorActivity.FLAG_APPOINTMENT -> {
                            view.setResult(CreateDoctorActivity.RESULT_APPOINTMENT)
                            view.finish()
                        }
                        CreateDoctorActivity.FLAG_ADD_DOCTOR -> {
                            view.setResult(MainActivity.REQUEST_DOCTOR)
                            view.finish()
                        }
                        CreateDoctorActivity.FLAG_SINGLEINTAKE -> {
                            var result = Intent()
                            result.putExtra(CreateSingleIntakeActivity.RESULT_DOCTOR, doctor)
                            view.setResult(CreateSingleIntakeActivity.RESULT_SINGLEINTAKE_DOCTOR, result)
                            view.finish()
                        }
                        CreateDoctorActivity.FLAG_MEDICATION -> {
                            var result = Intent()
                            result.putExtra(CreateMedicationActivity.RESULT_DOCTOR, doctor)
                            view.setResult(CreateMedicationActivity.RESULT_MEDICATION_DOCTOR, result)
                            view.finish()
                        }
                    }
                }
            }
        }
    }

    fun createDoctor(): Doctor {
        var mRealm = realmHelper.getRealm()
        var doctor = Doctor()
        doctor.id = realmHelper.getNextId(mRealm, Doctor::class.java)
        mRealm.close()
        return doctor
    }

    fun getData() {
        var intenData = view.intent
        if (intenData != null) {
            FLAG = intenData.getStringExtra(Constant.FLAG)
            when (FLAG) {
                CreateDoctorActivity.FLAG_EDIT -> {
                    var doctorEdit = intenData.getSerializableExtra(CreateDoctorActivity.DOCTOR) as Doctor
                    view?.OnBindData(doctorEdit)
                }
            }
        }
    }
}