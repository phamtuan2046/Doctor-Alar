package phamtuan.doctoralarm.controllers.medication.listener

import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.entity.Group
import phamtuan.doctoralarm.entity.Medication

/**
 * Created by P.Tuan on 11/21/2017.
 */
interface MedicationListener {
    fun OnLoadDoctorSuccess(doctors: ArrayList<Doctor>)

    fun OnLoadGroupSuccess(groups: ArrayList<Group>)

    fun OnGetBinData(medication: Medication)

    fun OnAddConfirm()
}