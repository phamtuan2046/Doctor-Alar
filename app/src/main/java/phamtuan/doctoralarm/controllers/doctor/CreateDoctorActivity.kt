package phamtuan.doctoralarm.controllers.doctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.create_doctor_activity.*
import org.jetbrains.anko.longToast
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.doctor.fragment.FragmentListDoctor
import phamtuan.doctoralarm.controllers.doctor.interactor.OnCreateDoctorView
import phamtuan.doctoralarm.controllers.doctor.prsenter.DoctorPresenter
import phamtuan.doctoralarm.entity.Doctor
import phamtuan.doctoralarm.utils.KeyboardUtil
import phamtuan.flashlightpro.main.BaseActivity

/**
 * Created by P.Tuan on 10/17/2017.
 */
class CreateDoctorActivity : BaseActivity(), OnCreateDoctorView {
    companion object {
        @JvmField val FLAG_APPOINTMENT = "add_doctor_from_appointment"
        @JvmField val RESULT_APPOINTMENT = 0

        @JvmField val FLAG_EDIT = "edit_doctor"
        @JvmField val DOCTOR = "doctor"
        @JvmField val RESULT_EDIT_DOCTOR = 1

        @JvmField val FLAG_SINGLEINTAKE = "add_doctor_from_sigleintake"
        @JvmField val FLAG_MEDICATION = "add_doctor_from_medication"

        @JvmField val FLAG_ADD_DOCTOR = "add_doctor"

        fun createIntentAddDoctor(context: Context):Intent{
            var intent = Intent(context,CreateDoctorActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_ADD_DOCTOR)
            return intent
        }

        fun createIntentAppointement(context: Context):Intent{
            var intent = Intent(context,CreateDoctorActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_APPOINTMENT)
            return intent
        }

        fun createIntentIntake(context: Context):Intent{
            var intent = Intent(context,CreateDoctorActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_SINGLEINTAKE)
            return intent
        }


        fun createIntentMedication(context: Context):Intent{
            var intent = Intent(context,CreateDoctorActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_MEDICATION)
            return intent
        }

        fun createIntentEditDcctor(context: Context,doctor:Doctor): Intent{
            var intent = Intent(context,CreateDoctorActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_EDIT)
            intent.putExtra(DOCTOR,doctor)
            return intent
        }
    }

    lateinit var presenter: DoctorPresenter
    var mCurrentDoctor: Doctor? = null
    var FLAG: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_doctor_activity)
        ButterKnife.bind(this)
        initview()
        presenter = DoctorPresenter(this, this)
        presenter.getData()
        btnAdddoctor.setOnClickListener({ view ->
            addDoctor()
        })

    }

    private fun initview() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.img_back)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun OnEmailExit() {
        longToast("Email already exists")
    }

    override fun OnBindData(doctorEdit: Doctor) {
        mCurrentDoctor = doctorEdit
        if (!mCurrentDoctor?.doctor_name.isNullOrEmpty())
            edName.setText(doctorEdit.doctor_name!!)
        if (!mCurrentDoctor?.specialization.isNullOrEmpty())
            edSpecialization.setText(doctorEdit.specialization!!)
        if (!mCurrentDoctor?.doctor_phone.isNullOrEmpty())
            edPhone.setText(doctorEdit.doctor_phone!!)
        if (!mCurrentDoctor?.doctor_address.isNullOrEmpty())
            edAddress.setText(doctorEdit.doctor_address!!)
        if (!mCurrentDoctor?.doctor_email.isNullOrEmpty())
            edMail.setText(doctorEdit.doctor_email!!)

    }

    private fun addDoctor() {
        KeyboardUtil.hideSoftKeyboard(this)
        if (!edName.text.toString().trim().isEmpty()) {
            if (mCurrentDoctor == null) mCurrentDoctor = presenter.createDoctor()
            mCurrentDoctor?.doctor_name = edName.text.toString()
            if (edSpecialization.text.toString().trim().isNotBlank())
                mCurrentDoctor?.specialization = edSpecialization.text.toString()
            if (edPhone.text.toString().trim().isNotBlank())
                mCurrentDoctor?.doctor_phone = edPhone.text.toString()
            if (edAddress.text.toString().trim().isNotBlank())
                mCurrentDoctor?.doctor_address = edAddress.text.toString()
            if (edMail.text.toString().trim().isNotBlank())
                mCurrentDoctor?.doctor_email = edMail.text.toString()
            presenter.addDoctor(mCurrentDoctor!!, FLAG)
        } else {
            Toast.makeText(this, "Please check name", Toast.LENGTH_LONG)
        }

    }
}