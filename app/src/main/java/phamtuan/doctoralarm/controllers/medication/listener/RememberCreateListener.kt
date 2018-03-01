package phamtuan.doctoralarm.controllers.medication.listener

import android.support.v4.app.DialogFragment
import phamtuan.doctoralarm.entity.Reminder

/**
 * Created by P.Tuan on 11/21/2017.
 */
interface RememberCreateListener {
    fun OnBack(dialog: DialogFragment)

    fun OnCreateRemeber(remember: Reminder, dialog: DialogFragment)

    fun OnEdited(remember: Reminder,dialog: DialogFragment)
}