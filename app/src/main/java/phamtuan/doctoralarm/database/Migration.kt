package jp.drjoy.app.data.repository.dao.realm

import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.RealmSchema

/**
 * Created by dr.joyno054 on 2017/06/07.
 */
class Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        if(realm == null) return

//        val schema: RealmSchema = realm.getSchema()
//        if (oldVersion.toInt() == 0) {
//            // Migrate from v0 to v1
//            oldVersion++
//        }
//
//        if (oldVersion.toInt() == 1) {
//            // Migrate from v1 to v2
//            oldVersion++
//        }
        if (oldVersion < newVersion) {
            throw IllegalStateException(String.format("Migration missing from v%d to v%d", oldVersion, newVersion))
        }
    }
}