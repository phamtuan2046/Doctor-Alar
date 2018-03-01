package phamtuan.doctoralarm.controllers.singleintake.interactor

import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.entity.Group

/**
 * Created by P.Tuan on 10/31/2017.
 */
interface SingleIntakeView {
    fun OnLoadDoctorSuccess(doctors: ArrayList<Doctor>)

    fun OnLoadGroupSuccess(groups: ArrayList<Group>)
}