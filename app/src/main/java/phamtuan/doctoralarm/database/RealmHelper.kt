package phamtuan.fingerdrawerx.database

import java.util.ArrayList

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import phamtuan.doctoralarm.entity.Doctor
import javax.inject.Inject

/**
 * Created by P.Tuan on 9/13/2017.
 */

class RealmHelper() {
    fun getRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    fun <T : RealmObject> createObject(realm: Realm, clazz: Class<T>): T {
        return createObject(realm, clazz, getNextId(realm, clazz))
    }

    fun <T : RealmObject> createObject(realm: Realm, clazz: Class<T>, id: Long): T {
        var model = realm.createObject(clazz,id)
        return realm.copyFromRealm(model)
    }

    fun <T : RealmObject> getNextId(realm: Realm, clazz: Class<T>): Long {
        var nextId: Long = 1
        val maxId = realm.where(clazz)?.max("id")
        if (maxId != null) {
            nextId = maxId.toLong() + 1
        }
        return nextId
    }

    fun <T : RealmObject> findAll(realm: Realm, cls: Class<T>): ArrayList<T> {
        val realmResults = realm.where(cls).findAll()
        val result = ArrayList<T>()
        result.addAll(realm.copyFromRealm(realmResults))
        return result
    }

    fun <T : RealmObject> addOrUpdateObj(realm: Realm, obj: T) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(obj)
        realm.commitTransaction()
    }


    fun checkDoctorExit(realm: Realm, doctor: Doctor): Boolean {
        if (doctor.doctor_email != null) {
            var result = realm?.where(Doctor::class.java).equalTo("doctor_email", doctor.doctor_email).findAll()
            if (!result.isEmpty()) return true
            else return false
        } else return false
    }

    fun <T: RealmObject> deleteObj(realm: Realm, clazz: Class<T>,id:Long){
      var obj = realm.where(clazz).equalTo("id",id).findFirst()
        realm.beginTransaction()
        obj?.deleteFromRealm()
        realm.commitTransaction()
    }

}
