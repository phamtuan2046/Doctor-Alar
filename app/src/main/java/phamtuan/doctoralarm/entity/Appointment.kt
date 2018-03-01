package phamtuan.doctoralarm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import phamtuan.doctoralarm.common.Constant
import java.io.Serializable
import java.util.*

/**
 * Created by P.Tuan on 10/10/2017.
 */
open class Appointment : RealmObject(), Serializable, ViewModel {
    @PrimaryKey
    var id: Long? = 0
    var doctor_id: Int? = 0
    var appoinmentDate: Date? = null
    var note: String? = null
    var state: Int = 0
    var doctor: Doctor? = null

    private var dateNotify: Date? = null

    override fun keyItem(): Int {
        return Constant.APPOIMENT_KEY
    }

    override fun getTime(): String? {
       return null
    }

    override fun getDate(): Date? {
        return this.appoinmentDate
    }
}