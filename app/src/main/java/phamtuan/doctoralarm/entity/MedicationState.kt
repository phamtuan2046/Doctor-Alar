package phamtuan.doctoralarm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import phamtuan.doctoralarm.common.Constant
import java.io.Serializable

/**
 * Created by P.Tuan on 10/10/2017.
 */
open class MedicationState: RealmObject() {
    @PrimaryKey
     var id: Long? = 0

     var created: String? = null

     var modified: String? = null

     var deleted: Int? = 0

     var date_active: String? = null

     var medication_state: Int? = 0

     var medication_id: Long? = 0

     var time_active: String? = null

     var dosage_state: String? = null

}