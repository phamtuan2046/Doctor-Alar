package phamtuan.doctoralarm.controllers.medication

import android.content.Intent
import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.commonbase.OnDateTimeListener
import phamtuan.doctoralarm.controllers.doctor.CreateDoctorActivity
import phamtuan.doctoralarm.controllers.group.CreateGroupActivity
import phamtuan.doctoralarm.controllers.main.MainActivity
import phamtuan.doctoralarm.controllers.medication.CreateMedicationActivity.Companion.FLAG_ADD
import phamtuan.doctoralarm.controllers.medication.CreateMedicationActivity.Companion.FLAG_EDIT
import phamtuan.doctoralarm.controllers.medication.inventory.InventoryActivity
import phamtuan.doctoralarm.controllers.medication.listener.MedicationListener
import phamtuan.doctoralarm.entity.ChildSchedule
import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.entity.Group
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.utils.DateUtil
import java.util.*
import kotlin.concurrent.fixedRateTimer

/**
 * Created by P.Tuan on 11/21/2017.
 */
class MedicationPresenter(val view: CreateMedicationActivity, val listener: MedicationListener) : BasePresenter() {
    companion object {
        val PLUS = 0
        val MINIUS = 1
        var FLAG = ""
    }
    fun getdata(){
        var intent = view.intent
        if(intent != null){
            FLAG = intent.getStringExtra(Constant.FLAG)
            if(FLAG.equals(FLAG_EDIT)){
                var medication = intent.getSerializableExtra(CreateMedicationActivity.MEDICATION) as Medication
                listener.OnGetBinData(medication)
            }else{
                listener.OnAddConfirm()
            }
        }
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

    fun setDate(calendar: Calendar, type: Int, flag: String, listener: OnDateTimeListener) {
        DateUtil.getDateTime(view, calendar, type, flag, listener)
    }

    fun caculatorday(input: Int, type: Int): Int {
        var output = input
        when (type) {
            MedicationPresenter.PLUS -> {
                output += 1
            }
            else -> {
                output += -1
            }
        }
        if (output < 0) output = 0
        return output
    }

    fun caculatorAfter(input: Int, type: Int): Int {
        var output = input
        when (type) {
            MedicationPresenter.PLUS -> {
                output += 1
            }
            else -> {
                output += -1
            }
        }
        if (output < 0) output = 0
        if (input > 99) output = 9
        return output
    }

    fun createDoctor() {
        var intent = CreateDoctorActivity.createIntentMedication(view)
        view?.startActivityForResult(intent, CreateMedicationActivity.RESULT_ADD_DOCTOR)
    }

    fun createGroup() {
        var intent = CreateGroupActivity.createIntentMedication(view)
        view?.startActivityForResult(intent, CreateMedicationActivity.RESULT_ADD_GROUP)
    }

    fun createMedication():Medication{
        var mRealm = realmHelper.getRealm()
        var medication = Medication()
        medication.id = realmHelper.getNextId(mRealm,Medication::class.java)
        mRealm.close()
        return medication
    }

    fun saveMedication(medication: Medication){
        var mRealm = realmHelper.getRealm()
        realmHelper.addOrUpdateObj(mRealm,medication)
        mRealm.close()
        when(FLAG){
            FLAG_ADD -> {
                view.setResult(MainActivity.REQUEST_MEDICATION)
                view.finish()
            }
            FLAG_EDIT -> {
                view.setResult(MainActivity.REQUEST_MEDICATION)
                view.finish()
            }
        }
    }
    fun createChildSchedule():ChildSchedule{
        var mRealm = realmHelper.getRealm()
        var child = ChildSchedule()
        child.id = realmHelper.getNextId(mRealm,ChildSchedule::class.java)
        mRealm.close()
        return child
    }
    fun getDaulftValues(currentUnit: String): String {
        var currentValues: Float
        when (currentUnit) {
            "Pill" -> {
                currentValues = 1f
            }
            "Mg" -> {
                currentValues = 50f
            }
            "Gr" -> {
                currentValues = 0.5f
            }
            "Ml" -> {
                currentValues = 50f
            }
            "Ltr" -> {
                currentValues = 0.5f
            }
            "Oz" -> {
                currentValues = 0.5f
            }
            "Patch" -> {
                currentValues = 1f
            }
            "Vial" -> {
                currentValues = 1f
            }
            else -> {
                currentValues = 1f
            }
        }
        return currentValues.toString()
    }
    fun addInventory(){
        var intent = Intent(view,InventoryActivity::class.java)
        view?.startActivity(intent)
    }

    fun checkvalidetTime(start:Date,end:Date):Boolean{
        if(start.time > end.time){
            return false
        }else{
            return true
        }
    }
    fun checktimeNow(start:Date,end:Date):Boolean{
        if(start.time < Calendar.getInstance().timeInMillis || end.time < Calendar.getInstance().timeInMillis){
            return false
        }else {
            return true
        }
    }

    fun deleteMedication(medication: Medication){
        var mRealm = realmHelper.getRealm()
        realmHelper.deleteObj(mRealm,Medication::class.java,medication.id!!)
        mRealm.close()
    }
}