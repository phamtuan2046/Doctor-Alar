package phamtuan.doctoralarm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by P.Tuan on 11/22/2017.
 */
open class ChildSchedule : RealmObject() {
    @PrimaryKey
    var id: Long? = null

    var sunCheck = false

    var monCheck= false

    var tuCheck= false

    var wenCheck = false

    var thCheck = false

    var frCheck = false

    var satCheck = false
}