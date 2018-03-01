package phamtuan.doctoralarm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by P.Tuan on 10/10/2017.
 */
open class Notification: RealmObject() {
    @PrimaryKey
     var id: Long? = 0

     var notificationId: Long? = 0

     var medicationId: Long? = 0

     var appoinmentId: Long? = 0

     var medicationStateId: Long? = 0

     var title: String? = null

     var content: String? = null

     var timeDisplay: String? = null

     var icon: Int? = 0

     var timeAlarm: Long? = 0

     var requestCode: Int? = 0

     var type: Int? = 0

     var typeNotify: Int? = 0// type notification of medication

     var notifyByType: Int? = 0// notify by date
}