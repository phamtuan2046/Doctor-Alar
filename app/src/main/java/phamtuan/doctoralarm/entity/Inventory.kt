package phamtuan.doctoralarm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

/**
 * Created by P.Tuan on 10/10/2017.
 */
open class Inventory : RealmObject() {
    @PrimaryKey
    var id: Int? = 0

    var created: String? = null

    var modified: String? = null

    var deleted: Int? = 0

    var medicaiton_id: Int? = 0

    var remaining_unit: String? = null

    var each_unit: String? = null

    var notice: String? = null

    var unit: String? = null
}