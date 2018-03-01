package phamtuan.doctoralarm.controllers.appointment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_create_appointment.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.commonbase.DoctorAdapter
import phamtuan.doctoralarm.controllers.commonbase.OnDateTimeListener
import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.utils.DateUtil
import phamtuan.flashlightpro.main.BaseActivity
import java.util.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import org.jetbrains.anko.longToast
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.entity.Appointment


/**
 * Created by P.Tuan on 10/22/2017.
 */
class CreateAppointmentActivity : BaseActivity(), CreateAppointmentListener, View.OnClickListener, OnDateTimeListener {
    companion object {
        @JvmField
        val FLAG_ADD = "add_appointment"
        @JvmField
        val FLAG_EDIT = "edit_appointment"
        @JvmField
        val APPOINTMENT_OBJECT = "edit_appointment"

        fun createIntentAddAppointment(context: Context): Intent {
            var intent = Intent(context, CreateAppointmentActivity::class.java)
            intent.putExtra(Constant.FLAG, FLAG_ADD)
            return intent
        }

        fun createIntentEditAppointment(context: Context, appointment: Appointment): Intent {
            var intent = Intent(context, CreateAppointmentActivity::class.java)
            intent.putExtra(Constant.FLAG, FLAG_ADD)
            intent.putExtra(APPOINTMENT_OBJECT, appointment)
            return intent
        }
    }

    lateinit var presenter: AppointmentPresenter
    lateinit var doctorAdapter: DoctorAdapter
    var mDoctors = ArrayList<Doctor>()
    var currentCalendar = Calendar.getInstance()
     var currentDoctor: Doctor? = null
     var currentAppointment: Appointment? = null
    var FLAG: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)
        ButterKnife.bind(this)
        presenter = AppointmentPresenter(this, this)
        initview()
        inittoolbar()
        setOnClick()
        presenter.getFlag()
    }

    override fun OnLoadDoctorSuccess(doctors: List<Doctor>) {
        mDoctors.clear()
        mDoctors.addAll(doctors)
        doctorAdapter.notifyDataSetChanged()
    }

    private fun inittoolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.img_back)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.AppbtnAddDoctor -> {
                presenter.addDoctor()
            }
            R.id.AppGetDate -> {
                presenter.setDate(currentCalendar, DateUtil.DATE, this)
            }
            R.id.appGetTime -> {
                presenter.setDate(currentCalendar, DateUtil.TIME, this)
            }
            R.id.appCreate -> {
                addAppointment()
            }
        }
    }

    override fun OnBindata(appointment: Appointment) {

    }

    private fun setOnClick() {
        AppGetDate.setOnClickListener(this)
        AppbtnAddDoctor.setOnClickListener(this)
        appGetTime.setOnClickListener(this)
        appCreate.setOnClickListener(this)
    }

    fun initview() {
        doctorAdapter = DoctorAdapter(this, mDoctors)
        spDoctorApp.adapter = doctorAdapter
        spDoctorApp.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                currentDoctor = mDoctors[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
            }

        })
        presenter.getDoctor()
        tvDate.text = DateUtil.converDateToString(currentCalendar.time, DateUtil.DATE_FORMAT_1)
        tvTime.text = DateUtil.converDateToString(currentCalendar.time, DateUtil.DATE_FORMAT_3)
    }

    private fun addAppointment() {
        if (currentDoctor == null || currentDoctor?.doctor_name.equals("None")) {
            longToast("Please create Doctor")
        } else if (currentCalendar.timeInMillis < Calendar.getInstance().timeInMillis) {
            longToast("Please check date time")
        } else {
            if (currentAppointment == null) currentAppointment = presenter.createAppointment()
            currentAppointment?.appoinmentDate = currentCalendar.time
            currentAppointment?.doctor = currentDoctor
            if (edNote.text.toString().isNullOrBlank()) {
                currentAppointment?.note = edNote.text.toString()
            }
            presenter.addAppointment(currentAppointment!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Constant.RESULT_APPOINTMENT -> {
                presenter.getDoctor()
            }
        }
    }
}