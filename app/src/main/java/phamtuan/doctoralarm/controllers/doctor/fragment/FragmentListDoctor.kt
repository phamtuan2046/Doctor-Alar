package phamtuan.doctoralarm.controllers.doctor.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_doctors.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.controllers.doctor.CreateDoctorActivity
import phamtuan.doctoralarm.controllers.doctor.adapter.DoctorsListAdapter
import phamtuan.doctoralarm.controllers.doctor.interactor.DoctorListListener
import phamtuan.doctoralarm.controllers.doctor.prsenter.ListDoctorPresenter
import phamtuan.doctoralarm.entity.Doctor
import phamtuan.flashlightpro.main.BaseFragment

/**
 * Created by P.Tuan on 10/26/2017.
 */
class FragmentListDoctor : BaseFragment(), DoctorListListener, DoctorsListAdapter.Interacter {

    companion object {
        val RESULT_DOCTOR = "doctor"
        fun newInstance(): FragmentListDoctor {
            return FragmentListDoctor()
        }
    }

    private var indexEdit: Int? = null
    private var presenter: ListDoctorPresenter? = null
    private var doctorAdapter: DoctorsListAdapter? = null
    private var mListDoctor = ArrayList<Doctor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ListDoctorPresenter(this, this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_list_doctors, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initdoctors()
        presenter?.getListDoctor()
    }

    override fun OnLoadDoctorSuccess(listDoctor: ArrayList<Doctor>) {
        mListDoctor.clear()
        mListDoctor.addAll(listDoctor)
        doctorAdapter?.notifyDataSetChanged()
    }

    override fun OnNewMessage(index: Int) {

    }

    override fun OnNewCall(index: Int) {

    }

    override fun OnEdit(index: Int) {
        indexEdit = index
        var intent = CreateDoctorActivity.createIntentEditDcctor(context, mListDoctor[index])
        startActivityForResult(intent, CreateDoctorActivity.RESULT_EDIT_DOCTOR)
    }

    override fun OnTodayClick() {

    }

    fun initdoctors() {
        doctorAdapter = DoctorsListAdapter(context, mListDoctor, this)
        rcvDoctors.adapter = doctorAdapter
    }
    fun reload(){
        presenter?.getListDoctor()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CreateDoctorActivity.RESULT_EDIT_DOCTOR -> {
                var doctor = data?.getSerializableExtra(RESULT_DOCTOR) as Doctor
                if (doctor != null) {
                    mListDoctor.removeAt(indexEdit!!)
                    doctorAdapter?.notifyItemRemoved(indexEdit!!)
                    mListDoctor.add(indexEdit!!, doctor!!)
                    doctorAdapter?.notifyItemInserted(indexEdit!!)
                }
            }
        }
    }
}