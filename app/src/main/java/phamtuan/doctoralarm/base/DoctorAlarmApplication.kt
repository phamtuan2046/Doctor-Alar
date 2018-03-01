package phamtuan.flashlightpro.main

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import io.realm.Realm
import io.realm.RealmConfiguration
import jp.drjoy.app.data.repository.dao.realm.Migration
import phamtuan.doctoralarm.di.component.ApplicationComponent
import phamtuan.doctoralarm.di.component.DaggerApplicationComponent
import phamtuan.flashlightpro.util.StoreData
import phamtuan.doctoralarm.di.module.ApplicationModule


/**
 * Created by P.Tuan on 10/7/2016.
 */
class DoctorAlarmApplication : Application() {
    var context: Context? = null
    lateinit var applicationComponent: ApplicationComponent
    var app: DoctorAlarmApplication? = null

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        instance = this
        context = applicationContext
        Realm.init(context)
        val realmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(Migration())
                .build()
//            Realm.deleteRealm(realmConfiguration)
        Realm.setDefaultConfiguration(realmConfiguration)
        data = StoreData(context!!)
//
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(instance!!)
    }

    companion object {
        @get:Synchronized var instance: DoctorAlarmApplication? = null
        var data: StoreData? = null
    }
}
