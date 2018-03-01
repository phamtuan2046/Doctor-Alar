package phamtuan.doctoralarm.entity

import java.io.Serializable

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Group : RealmObject(),Serializable {
    @PrimaryKey
    var id: Long? = 0
    var deleted: Int? = 0
    var created: String? = null
    var modified: String? = null
    var group_name: String? = null
    var medication_id: String? = null


}
