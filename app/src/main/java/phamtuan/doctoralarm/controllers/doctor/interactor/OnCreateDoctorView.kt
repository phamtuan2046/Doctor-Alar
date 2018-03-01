package phamtuan.doctoralarm.controllers.doctor.interactor

import phamtuan.doctoralarm.entity.Doctor

/**
 * Created by P.Tuan on 10/30/2017.
 */
interface OnCreateDoctorView {
    fun OnEmailExit()

    fun OnBindData(doctorEdit: Doctor)
}