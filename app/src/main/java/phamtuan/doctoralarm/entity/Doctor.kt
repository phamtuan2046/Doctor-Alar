package phamtuan.doctoralarm.entity

import android.os.Parcel
import android.os.Parcelable

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Doctor() : RealmObject(), Serializable {
    @PrimaryKey
    var id: Long? = null

    var deleted: Int? = 0

    var created: String? = null

    var modified: String? = null

    var doctor_name: String? = null

    var specialization: String? = null

    var doctor_phone: String? = null

    var doctor_address: String? = null

    var doctor_email: String? = null

    var note: String? = null

//    companion object CREATOR : Parcelable.Creator<Doctor> {
//        override fun createFromParcel(parcel: Parcel?): Doctor {
//            return Doctor(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Doctor?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//    constructor(parcel: Parcel?) : this() {
//        id = parcel?.readLong()
//        deleted = parcel?.readInt()
//        created = parcel?.readString()
//        modified = parcel?.readString()
//        doctor_name = parcel?.readString()
//        specialization = parcel?.readString()
//        doctor_phone = parcel?.readString()
//        doctor_address = parcel?.readString()
//        doctor_email = parcel?.readString()
//        note = parcel?.readString()
//    }
//
//    override fun writeToParcel(parcel: Parcel?, p1: Int) {
//        parcel?.writeLong(id!!)
//        parcel?.writeInt(deleted!!)
//        parcel?.writeString(created)
//        parcel?.writeString(modified)
//        parcel?.writeString(doctor_name)
//        parcel?.writeString(specialization)
//        parcel?.writeString(doctor_phone)
//        parcel?.writeString(doctor_address)
//        parcel?.writeString(doctor_email)
//        parcel?.writeString(note)
//    }
//
//    override fun describeContents(): Int {
//       return 0
//    }
}
