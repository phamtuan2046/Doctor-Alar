package phamtuan.doctoralarm.controllers.singleintake

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.add_single_intake.*
import org.jetbrains.anko.longToast
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.commonbase.DoctorAdapter
import phamtuan.doctoralarm.controllers.commonbase.OnDateTimeListener
import phamtuan.doctoralarm.controllers.medication.adapter.GroupAdapterSpinner
import phamtuan.doctoralarm.controllers.medication.adapter.InstructionAdapterSpinner
import phamtuan.doctoralarm.controllers.singleintake.interactor.SingleIntakeView
import phamtuan.doctoralarm.controllers.singleintake.prsenter.CreateSinglePresenter
import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.entity.Group
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.utils.DateUtil
import phamtuan.flashlightpro.main.BaseActivity
import java.util.*

/**
 * Created by P.Tuan on 10/22/2017.
 */
class CreateSingleIntakeActivity : BaseActivity(), SingleIntakeView, View.OnClickListener, OnDateTimeListener, DialogUnitListener {

    companion object {
        var FLAG_ADD = "add_singleIntake"

        var RESULT_SINGLEINTAKE_GROUP = 1
        var RESULT_SINGLEINTAKE_DOCTOR = 2
        var RESULT_DOCTOR = "doctor_result"
        var RESULT_GROUP = "group_result"

        fun createIntentAdd(context: Context):Intent{
            var intent = Intent(context,CreateSingleIntakeActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_ADD)
            return intent
        }

        fun createIntentEdit(context: Context,medication:Medication):Intent{
            var intent = Intent(context,CreateSingleIntakeActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_ADD)
            intent.putExtra("medication",medication)
            return intent
        }
    }

    private var presenter: CreateSinglePresenter? = null
    lateinit var doctorAdapter: DoctorAdapter
    lateinit var groupAdapter: GroupAdapterSpinner
    private var instructionAdapter: InstructionAdapterSpinner? = null
    var listDoctors = ArrayList<Doctor>()
    var currentDoctor: Doctor? = null

    var listGroups = ArrayList<Group>()
    var currentGroup: Group? = null
    var currentCalendar = Calendar.getInstance()
    var showdialog = false

    private var instruction: List<String>? = null
    var valueInstruction = "No Preference"

    var currentUnit = "Pill"
    var currentVauluesUnit = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_single_intake)
        presenter = CreateSinglePresenter(this, this)
        initToolbar()
        initGroup()
        initDoctor()
        initSpinnerInstruction()
        presenter?.getDoctor()
        presenter?.getGroup()
        setOnClick()
    }

    override fun OnLoadDoctorSuccess(doctors: ArrayList<Doctor>) {
        listDoctors.addAll(doctors)
        doctorAdapter.notifyDataSetChanged()
    }

    override fun OnLoadGroupSuccess(groups: ArrayList<Group>) {
        listGroups.addAll(groups)
        groupAdapter.notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnAddGroup -> {
                presenter?.addGroup()
            }
            R.id.btnAddDoctor -> {
                presenter?.addDoctor()
            }
            R.id.btnUnit -> {
                showDialog()
            }
            R.id.btnGetDate -> {
                presenter?.setDate(currentCalendar, DateUtil.DATE, this)
            }
            R.id.btnGetTime -> {
                presenter?.setDate(currentCalendar, DateUtil.TIME, this)
            }
            R.id.btnCreate -> {
                createSingleIntake()
            }
        }
    }

    override fun OnDateTime(calendar: Calendar, type: Int, flag: String) {
        currentCalendar = calendar
        when (type) {
            DateUtil.TIME -> {
                tvTime.text = DateUtil.converDateToString(currentCalendar.time, DateUtil.DATE_FORMAT_3)
            }
            DateUtil.DATE -> {
                tvDate.text = DateUtil.converDateToString(currentCalendar.time, DateUtil.DATE_FORMAT_1)
            }
        }
    }

    override fun OnValuseSuccess(values: Float, unit: String) {
        currentUnit = unit
        currentVauluesUnit = values
        tvUnit.text = values.toString() + " " + unit
        showdialog = false
    }

    override fun OnDismisDialog() {
        showdialog = false
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.img_back)
    }

    fun initGroup() {
        groupAdapter = GroupAdapterSpinner(this, listGroups)
        spGroup.adapter = groupAdapter
        spGroup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currentGroup = listGroups[p2]
            }
        }
    }

    fun initDoctor() {
        doctorAdapter = DoctorAdapter(this, listDoctors)
        spDoctor.adapter = doctorAdapter
        spDoctor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currentDoctor = listDoctors[p2]
            }
        }
    }

    private fun initSpinnerInstruction() {
        var arrUnit = this.resources.getStringArray(R.array.list_instrustion)
        var list: List<String> = ArrayList(Arrays.asList(*arrUnit))
        instruction = list
        instructionAdapter = InstructionAdapterSpinner(this, instruction!!)
        spNote.adapter = instructionAdapter
        spNote.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                valueInstruction = instruction!![p2]
            }
        }
    }

    fun setOnClick() {
        btnAddGroup.setOnClickListener(this)
        btnAddDoctor.setOnClickListener(this)
        btnUnit.setOnClickListener(this)
        btnGetDate.setOnClickListener(this)
        btnGetTime.setOnClickListener(this)
        btnCreate.setOnClickListener(this)
    }

    fun showDialog() {
        if (!showdialog) {
            showdialog = true
            var dialog = UnitDialog.newIntacnce(this, this)
            dialog.show(supportFragmentManager.beginTransaction(), null)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_SINGLEINTAKE_GROUP -> {
                if (data != null) {
                    var group = data?.getSerializableExtra(RESULT_GROUP) as Group
                    listGroups.add(group)
                    groupAdapter.notifyDataSetChanged()
                }

            }
            Constant.RESULT_SINGLEINTAKE_DOCTOR -> {
                if (data != null) {
                    var doctor = data?.getSerializableExtra(RESULT_DOCTOR) as Doctor
                    listDoctors.add(doctor)
                    doctorAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun createSingleIntake() {
        if (edNameMedication.text.toString().trim().isNotEmpty()) {
            if (presenter!!.checkDateTime(currentCalendar.time)) {
                var medication = presenter?.createMediacation()
                medication?.medication_name = edNameMedication.text.toString().trim()
                medication?.medication_type = Constant.SINGLE_INTAKE_KEY
                if (currentDoctor != null && !currentDoctor?.doctor_name.equals("None")) {
                    medication?.doctor = currentDoctor
                }
                if (currentGroup != null && !currentGroup?.group_name.equals("None")) {
                    medication?.group = currentGroup
                }
                medication?.titleInstruction = valueInstruction
                medication?.medication_instruction = spNote.selectedItemPosition
                medication?.unit = currentUnit
                medication?.values_unit = currentVauluesUnit.toString()
                medication?.start_date = currentCalendar.time
                presenter?.saveMedication(medication!!)
            }
        } else {
            longToast(resources.getString(R.string.alert_medication_name))
        }
    }

}