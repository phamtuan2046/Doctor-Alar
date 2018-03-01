package phamtuan.doctoralarm.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import org.joda.time.DateTimeFieldType
import org.joda.time.LocalDate
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.commonbase.OnDateTimeListener
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by P.Tuan on 10/23/2017.
 */

object DateUtil {
    val DATE_FORMAT_1 = "MM/dd/yyyy"
    val DATE_FORMAT_3 = "HH:mm"


    val DATE = 0;
    val TIME = 1;

    fun converDateToString(date: Date, format: String): String? {
        val dateFormat = SimpleDateFormat(format)
        try {
            return dateFormat.format(date)
        } catch (e: Exception) {
            return null
        }
    }

    fun getDateTime(context: Context, calendar: Calendar, type: Int, flag: String, listener: OnDateTimeListener) {
        when (type) {
            DATE -> {
                val dateCallback = object : DatePickerDialog.OnDateSetListener {
                    override
                    fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                        calendar.set(year, monthOfYear, dayOfMonth)
                        listener.OnDateTime(calendar, type, flag)
                    }
                }
                var datePicker = DatePickerDialog(context, R.style.datepickerDialog, dateCallback, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
                datePicker.show()
            }
            TIME -> {
                val timeCallback = object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        calendar.set(Calendar.HOUR, p1)
                        calendar.set(Calendar.MINUTE, p2)
                        listener.OnDateTime(calendar, type, flag)
                    }
                }
                var timePicker = TimePickerDialog(context, R.style.datepickerDialog, timeCallback, calendar[Calendar.HOUR], calendar[Calendar.MINUTE], true)
                timePicker.show()
            }
        }

    }

    fun isNow(date: LocalDate): Boolean {
        if (date.getValue(0) != LocalDate.now().getValue(0)) {
            return false
        }
        if (date.getValue(1) != LocalDate.now().getValue(1)) {
            return false
        }
        if (date.getValue(2) != LocalDate.now().getValue(2)) {
            return false
        }
        return true

    }
}
