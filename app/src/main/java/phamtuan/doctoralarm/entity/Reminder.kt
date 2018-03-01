package phamtuan.doctoralarm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by P.Tuan on 11/21/2017.
 */
open class Reminder : RealmObject() {
    @PrimaryKey
    var id: Long? = null
    var time: Date? = null
    var values: String? = null
    var unit: String? = null
}