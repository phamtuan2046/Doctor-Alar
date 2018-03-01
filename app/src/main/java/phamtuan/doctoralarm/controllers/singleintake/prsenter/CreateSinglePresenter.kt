package phamtuan.doctoralarm.controllers.singleintake.prsenter

import android.content.Intent
import com.ominext.basekotlin.components.base.presenter.BasePresenter
import org.jetbrains.anko.longToast
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.alarmmanager.AlarmHelper
import phamtuan.doctoralarm.alarmmanager.DoctorAlarmReceiver
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.commonbase.OnDateTimeListener
import phamtuan.doctoralarm.controllers.doctor.CreateDoctorActivity
import phamtuan.doctoralarm.controllers.group.CreateGroupActivity
import phamtuan.doctoralarm.controllers.singleintake.CreateSingleIntakeActivity
import phamtuan.doctoralarm.controllers.singleintake.interactor.SingleIntakeView
import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.entity.Group
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.utils.DateUtil
import java.util.*

/**
 * Created by P.Tuan on 10/31/2017.
 */
class CreateSinglePresenter(var view: CreateSingleIntakeActivity, var listener: SingleIntakeView) : BasePresenter() {
    lateinit var alarmHelper: AlarmHelper

    init {
        alarmHelper = AlarmHelper(view)
    }

    fun getDoctor() {
        var mRealm = realmHelper.getRealm()
        var doctors = realmHelper.findAll(mRealm, Doctor::class.java)
        if (doctors == null) doctors = ArrayList<Doctor>()
        var default = Doctor()
        default.doctor_name = "None"
        doctors.add(0, default)
        listener.OnLoadDoctorSuccess(doctors)
    }

    fun getGroup() {
        var mRealm = realmHelper.getRealm()
        var groups = realmHelper.findAll(mRealm, Group::class.java)
        mRealm.close()
        if (groups == null) groups = ArrayList<Group>()
        var default = Group()
        default.group_name = "None"
        groups.add(0, default)
        listener.OnLoadGroupSuccess(groups)
    }

    fun setDate(calendar: Calendar, type: Int, listener: OnDateTimeListener) {
        DateUtil.getDateTime(view, calendar, type,"singleintake", listener)
    }

    fun addGroup() {
        var i = CreateGroupActivity.createIntentIntake(view)
        view?.startActivityForResult(i, CreateSingleIntakeActivity.RESULT_SINGLEINTAKE_GROUP)
    }

    fun addDoctor() {
        var i = CreateDoctorActivity.createIntentIntake(view)
        view?.startActivityForResult(i, CreateSingleIntakeActivity.RESULT_SINGLEINTAKE_DOCTOR)
    }

    fun saveMedication(medication: Medication) {
        var mRealm = realmHelper.getRealm()
        realmHelper.addOrUpdateObj(mRealm,medication)
        mRealm.close()
        view?.finish()
    }

    fun createMediacation(): Medication {
        var mRealm = realmHelper.getRealm()
        var medication = Medication()
        medication.id = realmHelper.getNextId(mRealm, Medication::class.java)
        mRealm.close()
        return medication
    }
    fun checkDateTime(date: Date):Boolean{
        if (date.time < System.currentTimeMillis()) {
            view?.longToast(view!!.resources.getString(R.string.alert_single_intake))
            return false
        } else {
            return true
        }
    }
}