package phamtuan.doctoralarm.controllers.doctor.interactor

import phamtuan.doctoralarm.entity.Doctor

/**
 * Created by P.Tuan on 10/26/2017.
 */
interface DoctorListListener {
    fun OnLoadDoctorSuccess(listDoctor: ArrayList<Doctor>)
}