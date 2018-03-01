package phamtuan.doctoralarm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import phamtuan.doctoralarm.common.Constant
import java.io.Serializable
import java.util.*

/**
 * Created by P.Tuan on 10/10/2017.
 */
open class Medication : RealmObject(), ViewModel,Serializable {
    @PrimaryKey
    var id: Long? = 0

    var doctor: Doctor? = null

    var group:Group? = null

    var medication_instruction: Int? = 0

    var schedule_type: Int? = 0

    var schedule_every_count: Int? = 0

    var medication_type: Int? = 0

    var intakes: Int? = 0

    var medication_name: String? = null

    var unit: String? = null

    var unit_index: Int? = null

    var values_unit: String? = null

    var create_at: String? = null

    var titleInstruction: String? = null

    var schedule_kind: Int? = null

    var start_date_type: String? = null

    var start_date: Date? = null

    var start_date_medication_id: String? = null

    var end_date_type: Int? = null

    var end_date: Date? = null

    var child_schedule: ChildSchedule? = null
    override fun keyItem(): Int? {
        return return this.medication_type
    }

    override fun getTime(): String? {
        return null
    }

    override fun getDate(): Date? {
        return this.start_date
    }
}