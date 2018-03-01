package phamtuan.doctoralarm.di.component

import dagger.Component
import phamtuan.doctoralarm.di.module.ApplicationModule
import phamtuan.flashlightpro.main.DoctorAlarmApplication
import javax.inject.Singleton

/**
 * Created by P.Tuan on 10/19/2017.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(app: DoctorAlarmApplication)
}