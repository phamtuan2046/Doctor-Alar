package phamtuan.doctoralarm.controllers.appointment

import phamtuan.doctoralarm.entity.Appointment
import phamtuan.doctoralarm.entity.Doctor
import java.util.*

/**
 * Created by P.Tuan on 10/23/2017.
 */
interface CreateAppointmentListener {
    fun OnLoadDoctorSuccess(doctors: List<Doctor>)

    fun OnBindata(appointment: Appointment)
}