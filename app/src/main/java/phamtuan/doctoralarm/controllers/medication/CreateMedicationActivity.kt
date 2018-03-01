package phamtuan.doctoralarm.controllers.medication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.create_medication_activity.*
import kotlinx.android.synthetic.main.item_add_inventory.*
import kotlinx.android.synthetic.main.item_component.*
import kotlinx.android.synthetic.main.item_end_date.*
import kotlinx.android.synthetic.main.item_reminder_time.*
import kotlinx.android.synthetic.main.item_schedule.*
import kotlinx.android.synthetic.main.item_start_date.*
import org.jetbrains.anko.longToast
import org.joda.time.LocalDate
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.common.MedicationConstant
import phamtuan.doctoralarm.controllers.commonbase.DoctorAdapter
import phamtuan.doctoralarm.controllers.commonbase.OnDateTimeListener
import phamtuan.doctoralarm.controllers.medication.adapter.*
import phamtuan.doctoralarm.controllers.medication.listener.MedicationListener
import phamtuan.doctoralarm.controllers.medication.listener.RememberCreateListener
import phamtuan.doctoralarm.controllers.medication.remeber.ReminderDiaglogFragment
import phamtuan.doctoralarm.controllers.medication.viewholder.ReminderViewholder
import phamtuan.doctoralarm.entity.*
import phamtuan.doctoralarm.utils.DateUtil
import phamtuan.flashlightpro.main.BaseActivity
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by P.Tuan on 10/11/2017.
 */
class CreateMedicationActivity : BaseActivity(), MedicationListener, OnDateTimeListener, CompoundButton.OnCheckedChangeListener, RememberCreateListener, ReminderViewholder.Interactor {
    companion object {
        var FLAG_ADD = "add_medcaion"
        var FLAG_EDIT = "edit_medcaion"
        val MEDICATION = "medication"
        val RESULT_ADD_GROUP = 0
        val RESULT_ADD_DOCTOR = 1
        val RESULT_GROUP = "result_group"
        val RESULT_MEDICATION_GROUP = 1

        val RESULT_MEDICATION_DOCTOR = 2
        val RESULT_DOCTOR = "result_doctor"
        val START = "start"
        val END = "end"
        val REMINDER = "reminder"

        fun createIntentAddMedication(context: Context): Intent {
            var intent = Intent(context, CreateMedicationActivity::class.java)
            intent.putExtra(Constant.FLAG, FLAG_ADD)
            return intent
        }

        fun createIntentEditMedication(context: Context, medicaton: Medication): Intent {
            var intent = Intent(context, CreateMedicationActivity::class.java)
            intent.putExtra(Constant.FLAG, FLAG_EDIT)
            intent.putExtra(MEDICATION, medicaton)
            return intent
        }
    }

    private var unitAdapter: UnitAdapterSpinner? = null
    private var units: List<String>? = null
    private var instructionAdapter: InstructionAdapterSpinner? = null
    private var instruction: List<String>? = null
    private var timeUnitAdapter: TimeUnitSpinnerAdapter? = null
    private var timeUnits: List<String>? = null
    private val TAG = "MedicationActivity"
    lateinit var doctorAdapter: DoctorAdapter
    lateinit var groupAdapter: GroupAdapterSpinner
    var listDoctors = ArrayList<Doctor>()
    var listGroups = ArrayList<Group>()
    var presenter: MedicationPresenter? = null
    var currentCalendarStart = Calendar.getInstance()
    var currentCalendarEnd = Calendar.getInstance()
    var showDialog = false
    var listReminder = ArrayList<Reminder>()
    var reminderAdapter: ReminderAdapter? = null
    var currentReminderIdex: Int? = null
    var medicationEdit: Medication? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_medication_activity)
        presenter = MedicationPresenter(this, this)
        initview()
        initSpinnerUnit()
        initSpinnerInstruction()
        initTimeSpinner()
        initSpinerGroup()
        initDoctorspiner()
        OnClick()
        initReminderView()
        presenter?.getdata()
    }

    override fun OnGetBinData(medication: Medication) {
        medicationEdit = medication
        btnDelete.visibility = View.VISIBLE
        edtName.setText(medication.medication_name)
        if (medication.doctor != null) {
            spDoctor.setSelection(listDoctors.indexOf(medication.doctor!!))
        }
        if (medication.group != null) {
            spGroup.setSelection(listGroups.indexOf(medication.group!!))
        }
        spUnit.setSelection(medication.unit_index!!)
        spNote.setSelection(medication.medication_instruction!!)
        when (medication.schedule_type) {
            MedicationConstant.SCHEDULE_EVERYDAY -> {
                spTimeUnit.setSelection(medication.schedule_kind!!)
                edNumberday.setText(medication.schedule_every_count.toString())
            }
            else -> {
                binToggleButton(medication.child_schedule!!)
            }
        }
        tvStartDate.text = DateUtil.converDateToString(medication.start_date!!, DateUtil.DATE_FORMAT_1)
        if (medication.end_date != null) tvEndDate.text = DateUtil.converDateToString(medication.end_date!!, DateUtil.DATE_FORMAT_1)
    }

    override fun OnAddConfirm() {
        btnDelete.visibility = View.GONE
    }

    override fun OnLoadDoctorSuccess(doctors: ArrayList<Doctor>) {
        listDoctors.addAll(doctors)
        doctorAdapter.notifyDataSetChanged()
    }

    override fun OnLoadGroupSuccess(groups: ArrayList<Group>) {
        listGroups.addAll(groups)
        groupAdapter.notifyDataSetChanged()
    }

    override fun OnDateTime(calendar: Calendar, type: Int, flag: String) {
        when (flag) {
            START -> {
                currentCalendarStart = calendar
                tvStartDate.text = DateUtil.converDateToString(currentCalendarStart.time, DateUtil.DATE_FORMAT_1)
            }
            END -> {
                currentCalendarEnd = calendar
                tvEndDate.text = DateUtil.converDateToString(currentCalendarEnd.time, DateUtil.DATE_FORMAT_1)
            }
            REMINDER -> {
                listReminder[currentReminderIdex!!].time = calendar.time
                reminderAdapter?.notifyItemChanged(currentReminderIdex!!)
            }
        }
    }

    private fun initview() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.img_back)
        tvStartDate.text = DateUtil.converDateToString(currentCalendarStart.time, DateUtil.DATE_FORMAT_1)
        tvEndDate.text = DateUtil.converDateToString(currentCalendarEnd.time, DateUtil.DATE_FORMAT_1)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initSpinnerUnit() {
        var arrUnit = this.resources.getStringArray(R.array.spinner_dosage)
        var listunit: List<String> = ArrayList(Arrays.asList(*arrUnit))
        units = listunit
        unitAdapter = UnitAdapterSpinner(this, units!!)
        spUnit.adapter = unitAdapter
    }

    private fun initSpinnerInstruction() {
        var arrUnit = this.resources.getStringArray(R.array.list_instrustion)
        var list: List<String> = ArrayList(Arrays.asList(*arrUnit))
        instruction = list
        instructionAdapter = InstructionAdapterSpinner(this, instruction!!)
        spNote.adapter = instructionAdapter
    }

    private fun initTimeSpinner() {
        var arrUnit = this.resources.getStringArray(R.array.spinner_every)
        var list: List<String> = ArrayList(Arrays.asList(*arrUnit))
        timeUnits = list
        timeUnitAdapter = TimeUnitSpinnerAdapter(this, timeUnits!!)
        spTimeUnit.adapter = timeUnitAdapter
    }

    fun initDoctorspiner() {
        doctorAdapter = DoctorAdapter(this, listDoctors)
        spDoctor.adapter = doctorAdapter
        spDoctor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
        presenter?.getDoctor()
    }

    fun initSpinerGroup() {
        groupAdapter = GroupAdapterSpinner(this, listGroups)
        spGroup.adapter = groupAdapter
        spGroup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
        presenter?.getGroup()
    }

    fun OnClick() {
        btnAddGroup.setOnClickListener {
            presenter?.createGroup()
        }
        btnAddDoctor.setOnClickListener {
            presenter?.createDoctor()
        }
        btnSubNumberday.setOnClickListener {
            rdEvery.isChecked = true
            edNumberday.setText(presenter?.caculatorday(edNumberday.text.toString().toInt(), MedicationPresenter.MINIUS).toString())
        }
        btnAddNumberday.setOnClickListener {
            rdEvery.isChecked = true
            edNumberday.setText(presenter?.caculatorday(edNumberday.text.toString().toInt(), MedicationPresenter.PLUS).toString())
        }
        btnDateStart.setOnClickListener {
            presenter?.setDate(currentCalendarStart, DateUtil.DATE, START, this)
        }
        btnEndate.setOnClickListener {
            rbEnDate.isChecked = true
            presenter?.setDate(currentCalendarEnd, DateUtil.DATE, END, this)
        }
        btnSubAfter.setOnClickListener {
            rbAfter.isChecked = true
            tvIntake.setText(presenter?.caculatorAfter(tvIntake.text.toString().toInt(), MedicationPresenter.MINIUS).toString())
        }
        btnAddAfter.setOnClickListener {
            rbAfter.isChecked = true
            tvIntake.setText(presenter?.caculatorAfter(tvIntake.text.toString().toInt(), MedicationPresenter.PLUS).toString())
        }
        btnAddReminder.setOnClickListener {
            if (!showDialog) {
                showDialog = true
                var dialog = ReminderDiaglogFragment.newInstance(units!![spUnit!!.selectedItemPosition], this)
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.timepicker)
                dialog.show(supportFragmentManager, null)
            }
        }
        btnAddInventory.setOnClickListener {
            presenter?.addInventory()
        }
        btnCreateMedication.setOnClickListener {
            createMedcaiton()
        }
        btnDelete.setOnClickListener {
            presenter?.deleteMedication(medicationEdit!!)
        }
        btnSun.setOnCheckedChangeListener(this)
        btnMon.setOnCheckedChangeListener(this)
        btntu.setOnCheckedChangeListener(this)
        btnWen.setOnCheckedChangeListener(this)
        btnTh.setOnCheckedChangeListener(this)
        btnFr.setOnCheckedChangeListener(this)
        btnSat.setOnCheckedChangeListener(this)
    }

    override fun OnDelete(index: Int) {
        listReminder.removeAt(index)
        reminderAdapter?.notifyItemRemoved(index)
    }

    override fun OnGetTime(index: Int) {
        currentReminderIdex = index
        var calendar = Calendar.getInstance()
        calendar.time = listReminder[index].time
        presenter?.setDate(calendar, DateUtil.TIME, REMINDER, this)
    }

    override fun OnEdit(index: Int) {
        currentReminderIdex = index
        if (!showDialog) {
            showDialog = true
            var dialog = ReminderDiaglogFragment.newInstance(listReminder[index], this)
            dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.timepicker)
            dialog.show(supportFragmentManager, null)
        }
    }

    override fun OnBack(dialog: DialogFragment) {
        dialog.dismiss()
        showDialog = false
    }

    override fun OnCreateRemeber(remember: Reminder, dialog: DialogFragment) {
        dialog.dismiss()
        showDialog = false
        listReminder.add(remember)
        reminderAdapter?.notifyItemInserted(listReminder.size)
    }

    override fun OnEdited(remember: Reminder, dialog: DialogFragment) {
        listReminder.removeAt(currentReminderIdex!!)
        listReminder.add(currentReminderIdex!!, remember)
        reminderAdapter?.notifyItemChanged(currentReminderIdex!!)
        dialog.dismiss()
    }

    fun initReminderView() {
        var reminder = Reminder()
        reminder.values = presenter?.getDaulftValues(units!![spUnit.selectedItemPosition])
        reminder.unit = units!![spUnit.selectedItemPosition]
        reminder.time = Calendar.getInstance().time
        listReminder.add(reminder)
        reminderAdapter = ReminderAdapter(this, listReminder, this)
        rcvReminder.adapter = reminderAdapter
    }

    fun createMedcaiton() {
        if (vailidet()) {
            for (i in 0..listReminder.size - 1) {
                var reminder = listReminder[i]
                var medication = presenter?.createMedication()
                medication?.medication_name = edtName.text.toString()
                if (!listGroups[spGroup.selectedItemPosition].group_name.equals("None"))
                    medication?.group = listGroups[spGroup.selectedItemPosition]
                if (!listDoctors[spDoctor.selectedItemPosition].doctor_name.equals("None")) {
                    medication?.doctor = listDoctors[spDoctor.selectedItemPosition]
                }
                medication?.unit_index = spUnit.selectedItemPosition
                medication?.unit = units!![spUnit.selectedItemPosition]
                medication?.values_unit = reminder.values
                medication?.titleInstruction = units!![spUnit.selectedItemPosition]
                medication?.medication_instruction = spUnit.selectedItemPosition

                if (rdEvery.isChecked) {
                    medication?.schedule_type = MedicationConstant.SCHEDULE_EVERYDAY
                    medication?.schedule_every_count = edNumberday.text.toString().toInt()
                    medication?.schedule_kind = spTimeUnit.selectedItemPosition
                } else {
                    medication?.schedule_type = MedicationConstant.SCHEDULE_SPECIFIX
                    medication?.child_schedule = createChildSchedule()
                }
                medication?.start_date = currentCalendarStart.time
                medication?.start_date!!.hours = reminder.time!!.hours
                medication?.start_date!!.minutes = reminder.time!!.minutes
                if (rbEnDate.isChecked) {
                    medication?.end_date_type = MedicationConstant.END_DATE_SPECIFIC
                    medication?.end_date = currentCalendarEnd.time
                } else if (rbAfter.isChecked) {
                    medication?.end_date_type = MedicationConstant.END_DATE_AFTER
                    medication?.end_date = getEndDate()
                } else {
                    medication?.end_date_type = MedicationConstant.END_DATE_INDEFINITE
                }
                presenter?.saveMedication(medication!!)
            }
        }
    }

    fun getEndDate(): Date {
        var intakes = tvIntake.text.toString().toInt()
        var startDate = LocalDate(currentCalendarStart.timeInMillis)
        val numberDate: Int
        val numberMoth: Int
        var endDate: Date
        if (rdEvery.isChecked) {
            var number = edNumberday.text.toString().toInt()
            when (spTimeUnit.selectedItemPosition) {
                0 -> {
                    numberDate = number * intakes - 1
                    endDate = startDate.plusDays(numberDate).toDate()
                }
                1 -> {
                    numberDate = number * intakes * 7 - 1
                    endDate = startDate.plusDays(numberDate).toDate()
                }
                else -> {
                    numberMoth = intakes * number - 1
                    endDate = startDate.plusMonths(numberMoth).toDate()
                }
            }
        } else {
            endDate = getEndDateFromIntakesWithScheduleSpecific(intakes)
        }
        return endDate
    }

    fun getEndDateFromIntakesWithScheduleSpecific(numberIntakes: Int): Date {
        val numberDay: Float
        var intakesOnWeek = 0f
        if (btnSun.isChecked) {
            intakesOnWeek += 1
        }
        if (btnMon.isChecked) {
            intakesOnWeek += 1
        }
        if (btntu.isChecked) {
            intakesOnWeek += 1
        }
        if (btnWen.isChecked) {
            intakesOnWeek += 1
        }
        if (btnTh.isChecked) {
            intakesOnWeek += 1
        }
        if (btnFr.isChecked) {
            intakesOnWeek += 1
        }
        if (btnSat.isChecked) {
            intakesOnWeek += 1
        }
        numberDay = (numberIntakes / intakesOnWeek).toFloat() * 7
        var startdate = LocalDate(currentCalendarStart.timeInMillis)
        return startdate.plusDays(Math.round(numberDay)).toDate()
    }

    fun createChildSchedule(): ChildSchedule {
        var schedule = presenter?.createChildSchedule()
        if (btnSun.isChecked) {
            schedule?.sunCheck = true
        }
        if (btnMon.isChecked) {
            schedule?.monCheck = true
        }
        if (btntu.isChecked) {
            schedule?.tuCheck = true
        }
        if (btnWen.isChecked) {
            schedule?.wenCheck = true
        }
        if (btnTh.isChecked) {
            schedule?.thCheck = true
        }
        if (btnFr.isChecked) {
            schedule?.frCheck = true
        }
        if (btnSat.isChecked) {
            schedule?.satCheck = true
        }
        return schedule!!
    }

    fun vailidet(): Boolean {
        if (edtName.text.toString().isNullOrEmpty()) {
            longToast(resources.getString(R.string.alert_medication_name))
            return false
        } else if (rbEnDate.isChecked && !presenter!!.checktimeNow(currentCalendarStart.time, currentCalendarEnd.time)) {
            longToast(resources.getString(R.string.alert_medication_date_now))
            return false
        } else if (rbEnDate.isChecked && !presenter!!.checkvalidetTime(currentCalendarStart.time, currentCalendarEnd.time)) {
            longToast(resources.getString(R.string.alert_medication_date))
            return false
        } else if (rdspecific.isChecked && !checkObject()) {
            longToast(resources.getString(R.string.alert_medication_specific_day))
            return false
        } else {
            return true
        }
    }

    fun checkObject(): Boolean {
        var checked = false
        if (btnSun.isChecked) {
            checked = true
        }
        if (btnMon.isChecked) {
            checked = true
        }
        if (btntu.isChecked) {
            checked = true
        }
        if (btnWen.isChecked) {
            checked = true
        }
        if (btnTh.isChecked) {
            checked = true
        }
        if (btnFr.isChecked) {
            checked = true
        }
        if (btnSat.isChecked) {
            checked = true
        }
        return checked
    }

    fun binToggleButton(child: ChildSchedule) {
        btnSun.isChecked = child.sunCheck!!
        btnMon.isChecked = child.monCheck!!
        btntu.isChecked = child.tuCheck!!
        btnWen.isChecked = child.wenCheck!!
        btnTh.isChecked = child.thCheck!!
        btnFr.isChecked = child.frCheck!!
        btnSat.isChecked = child.satCheck!!
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        rdspecific.isChecked = true
        when (p0?.id) {
            R.id.btnSun -> {
            }
            R.id.btnMon -> {
            }
            R.id.btntu -> {
            }
            R.id.btnWen -> {
            }
            R.id.btnTh -> {
            }
            R.id.btnFr -> {
            }
            R.id.btnSat -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            when (resultCode) {
                RESULT_MEDICATION_GROUP -> {
                    var group = data.getSerializableExtra(RESULT_GROUP) as Group
                    listGroups.add(group)
                    groupAdapter.notifyDataSetChanged()
                    spGroup.setSelection(listGroups.size)
                }
                RESULT_MEDICATION_DOCTOR -> {
                    var doctor = data.getSerializableExtra(RESULT_DOCTOR) as Doctor
                    listDoctors.add(doctor)
                    doctorAdapter.notifyDataSetChanged()
                    spDoctor.setSelection(listDoctors.size)
                }
            }
        }
    }
}