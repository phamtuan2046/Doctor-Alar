package phamtuan.doctoralarm.controllers.appointment

import android.content.Intent
import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.commonbase.OnDateTimeListener
import phamtuan.doctoralarm.controllers.doctor.CreateDoctorActivity
import phamtuan.doctoralarm.entity.Appointment
import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.utils.DateUtil
import java.util.*


/**
 * Created by P.Tuan on 10/23/2017.
 */
class AppointmentPresenter(var view: CreateAppointmentActivity, var listener: CreateAppointmentListener) : BasePresenter() {
    var calendar = Calendar.getInstance()
    var FLAG: String? = null
    fun getDoctor() {
        var mReaml = realmHelper.getRealm()
        var doctors = realmHelper.findAll(mReaml, Doctor::class.java)
        if (doctors == null) doctors = ArrayList<Doctor>()
        var default = Doctor()
        default.doctor_name = "None"
        doctors.add(0, default)
        listener.OnLoadDoctorSuccess(doctors)
    }

    fun setDate(calendar: Calendar, type: Int, listener: OnDateTimeListener) {
        DateUtil.getDateTime(view, calendar, type,"appointment", listener)
    }

    fun addDoctor() {
        var i = CreateDoctorActivity.createIntentAppointement(view)
        view?.startActivityForResult(i, CreateDoctorActivity.RESULT_APPOINTMENT)
    }

    fun addAppointment(appointment: Appointment) {
        var mReaml = realmHelper.getRealm()
        when (FLAG) {
            CreateAppointmentActivity.FLAG_EDIT -> {
                realmHelper.addOrUpdateObj(mReaml,appointment)
                mReaml.close()
                var intent = Intent()
                intent.putExtra("",appointment)
                view?.setResult(1,intent)
                view?.finish()
            }
            CreateAppointmentActivity.FLAG_ADD -> {
                realmHelper.addOrUpdateObj(mReaml,appointment)
                mReaml.close()
                view?.finish()
            }
        }
    }

    fun createAppointment(): Appointment {
        var mReaml = realmHelper.getRealm()
        var appointment = Appointment()
        appointment.id = realmHelper.getNextId(mReaml, Appointment::class.java)
        mReaml.close()
        return appointment
    }

    fun getFlag() {
        var intent = view?.intent
        FLAG = intent.getStringExtra(Constant.FLAG)
        when (FLAG) {
            CreateAppointmentActivity.FLAG_EDIT -> {
                var appointment = intent.getSerializableExtra(CreateAppointmentActivity.APPOINTMENT_OBJECT) as Appointment
                listener?.OnBindata(appointment)
            }
        }
    }
}