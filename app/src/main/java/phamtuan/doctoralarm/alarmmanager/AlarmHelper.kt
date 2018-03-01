package phamtuan.doctoralarm.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

/**
 * Created by P.Tuan on 11/1/2017.
 */
class AlarmHelper(var context: Context) {

    companion object {
        @JvmField
        val REQUESTCODE = 100
    }
    lateinit var alarmManager: AlarmManager
    init {
       var alarmManager = context.getSystemService(Context.ALARM_SERVICE)
    }
    fun setAlarm(intent: Intent,date:Date) {
        var pendingIntent = PendingIntent.getBroadcast(context,REQUESTCODE,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.set(AlarmManager.RTC_WAKEUP,date.time,pendingIntent)
    }
}