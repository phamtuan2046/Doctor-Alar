package phamtuan.doctoralarm.controllers.daily.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_daily.*
import org.joda.time.LocalDate
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.appointment.CreateAppointmentActivity
import phamtuan.doctoralarm.controllers.daily.DailyContentListener
import phamtuan.doctoralarm.controllers.daily.DailyContentPresenter
import phamtuan.doctoralarm.controllers.daily.adapter.DailyItemAdapter
import phamtuan.doctoralarm.controllers.dialog.OptionDialog
import phamtuan.doctoralarm.controllers.medication.CreateMedicationActivity
import phamtuan.doctoralarm.controllers.singleintake.CreateSingleIntakeActivity
import phamtuan.doctoralarm.entity.Appointment
import phamtuan.doctoralarm.entity.Medication
import phamtuan.doctoralarm.entity.ViewModel
import phamtuan.doctoralarm.utils.DateUtil

/**
 * Created by P.Tuan on 10/10/2017.
 */
class DailyFragmentContent : Fragment() , DailyContentListener, DailyItemAdapter.Interactor {
    var mDate: LocalDate? = null
    var mAdapter: DailyItemAdapter? = null
    var mListData = ArrayList<ViewModel>()


    lateinit var presenter: DailyContentPresenter

    companion object {
        var RESULT_MEDICATION = 0
        var RESULT_SINGLE = 1
        var RESULT_APPOINTMENT = 2
        fun newInstace(localDate: LocalDate): Fragment {
            var fragment = DailyFragmentContent()
            fragment.mDate = localDate
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_daily, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mListData.clear()
        presenter = DailyContentPresenter(this,this)
        initView()
        presenter.getAlldata()
    }

    override fun OnMedicaionClick(index: Int) {
        if(DateUtil.isNow(mDate!!)){
            var dialog = OptionDialog.newInstance()
            dialog.show(fragmentManager,null)
        }else{
            var medication = mListData[index] as Medication
            var intent = CreateMedicationActivity.createIntentEditMedication(context,medication)
            startActivityForResult(intent,RESULT_MEDICATION)
        }
    }

    override fun OnSingleIntakeClick(index: Int) {
        if(DateUtil.isNow(mDate!!)){

        }else{
            var medication = mListData[index] as Medication
            var intent = CreateSingleIntakeActivity.createIntentEdit(context,medication)
            startActivityForResult(intent,RESULT_SINGLE)
        }
    }

    override fun OnAppointmentclick(index: Int) {
        if(DateUtil.isNow(mDate!!)){

        }else{
            var appointment = mListData[index] as Appointment
            var intent = CreateAppointmentActivity.createIntentEditAppointment(context,appointment)
            startActivityForResult(intent,RESULT_APPOINTMENT)
        }
    }

    override fun OnLoadDataSuccess(listData: ArrayList<ViewModel>) {
        mListData.addAll(listData)
        mAdapter?.notifyItemRangeChanged(mListData.size -listData.size,mListData.size)
    }

    fun initView() {
        mAdapter = DailyItemAdapter(context, mListData,this)
        rcvEvent.adapter = mAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}