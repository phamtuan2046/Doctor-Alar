package phamtuan.doctoralarm.controllers.commonbase

import java.util.*

/**
 * Created by P.Tuan on 10/24/2017.
 */
interface OnDateTimeListener {
    fun OnDateTime(calendar: Calendar,type: Int,flag:String)
}