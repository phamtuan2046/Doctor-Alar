package phamtuan.doctoralarm.controllers.medication.remeber

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TimePicker
import kotlinx.android.synthetic.main.dialog_remember.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.medication.listener.RememberCreateListener
import phamtuan.doctoralarm.entity.Reminder
import java.util.*

/**
 * Created by P.Tuan on 11/21/2017.
 */
class ReminderDiaglogFragment : DialogFragment(), ReminderPreseneterListener {
    var mCalendar = Calendar.getInstance()
    var currentUnit: String? = null
    var mListener: RememberCreateListener? = null
    var presenter: ReminderPresenter? = null
    var currentValues = 1f
    var mReminder: Reminder? = null
    var edit = false


    companion object {
        fun newInstance(unit: String, listener: RememberCreateListener): ReminderDiaglogFragment {
            var dialog = ReminderDiaglogFragment()
            dialog.currentUnit = unit
            dialog.mListener = listener
            return dialog
        }

        fun newInstance(reminder: Reminder, listener: RememberCreateListener): ReminderDiaglogFragment {
            var dialog = ReminderDiaglogFragment()
            dialog.mReminder = reminder
            dialog.mListener = listener
            dialog.edit = true
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ReminderPresenter(this, this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.dialog_remember, container, false)
        val displayRectangle = Rect()
        dialog.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        view?.minimumWidth = (displayRectangle.width() * 0.98f).toInt()
        view?.minimumHeight = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mReminder != null) {
            currentValues = mReminder!!.values!!.toFloat()
            currentUnit = mReminder!!.unit
            mCalendar.set(Calendar.HOUR,mReminder!!.time!!.hours)
            mCalendar.set(Calendar.MINUTE,mReminder!!.time!!.minutes)
            timePicker.currentHour = mReminder!!.time!!.hours
            timePicker.currentMinute = mReminder!!.time!!.minutes
        } else {
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
                "Unit" -> {
                    currentValues = 1f
                }
            }
        }

        tvValues.setText(subValue((currentValues.toString())))
        timePicker.setOnTimeChangedListener(object : TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(p0: TimePicker?, hour: Int, minute: Int) {
                mCalendar.set(Calendar.HOUR, hour)
                mCalendar.set(Calendar.MINUTE, minute)
            }
        })
        btnBack.setOnClickListener { mListener?.OnBack(this) }
        btnSub.setOnClickListener {
            if (!tvValues.text.toString().isNullOrBlank()) currentValues = tvValues.text.toString().toFloat()
            currentValues = unitDosage(currentUnit!!, currentValues, Operator.minus)
            if (currentValues < 0) currentValues = 0f
            tvValues.setText(subValue((currentValues.toString())))
        }
        btnAdd.setOnClickListener {
            if (!tvValues.text.toString().isNullOrBlank()) currentValues = tvValues.text.toString().toFloat()
            currentValues = unitDosage(currentUnit!!, currentValues, Operator.Plus)
            tvValues.setText(subValue((currentValues.toString())))
        }
        btnDone.setOnClickListener {
            if(edit){
                mReminder?.time = mCalendar.time
                mReminder?.values = tvValues.text.toString()
                mListener?.OnEdited(mReminder!!,this)
            }else{
                var remeber = presenter?.createRemeber()
                remeber?.time = mCalendar.time
                remeber?.unit = currentUnit
                remeber?.values = tvValues.text.toString()
                presenter?.save(remeber!!)
                mListener?.OnCreateRemeber(remeber!!, this)

            }
        }
    }

    fun unitDosage(unit: String, input: Float, operator: Operator): Float {
        var output = input
        if (operator == Operator.Plus) {
            when (unit) {
                "Pill" -> {
                    output += 1f
                }
                "Mg" -> {
                    output += 50f
                }
                "Gr" -> {
                    output += 0.5f
                }
                "Ml" -> {
                    output += 50f
                }
                "Ltr" -> {
                    output += 0.5f
                }
                "Oz" -> {
                    output += 0.5f
                }
                "Patch" -> {
                    output += 1f
                }
                "Vial" -> {
                    output += 1f
                }
                "Unit" -> {
                    output += 1f
                }
            }
        } else {
            when (unit) {
                "Pill" -> {
                    output -= 1f
                }
                "Mg" -> {
                    output -= 50f
                }
                "Gr" -> {
                    output -= 0.5f
                }
                "Ml" -> {
                    output -= 50f
                }
                "Ltr" -> {
                    output -= 0.5f
                }
                "Oz" -> {
                    output -= 0.5f
                }
                "Patch" -> {
                    output -= 1f
                }
                "Vial" -> {
                    output -= 1f
                }
                "Unit" -> {
                    output -= 1f
                }
            }
        }
        return output
    }

    enum class Operator {
        Plus,
        minus
    }

    fun subValue(values: String): String {
        if (values.contains(".0")) {
            return values.replace(".0", "")
        } else {
            return values
        }
    }
}