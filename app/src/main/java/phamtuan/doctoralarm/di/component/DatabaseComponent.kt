package phamtuan.doctoralarm.di.component

import com.ominext.basekotlin.components.base.presenter.BasePresenter
import dagger.Component
import phamtuan.doctoralarm.di.module.ApplicationModule
import phamtuan.doctoralarm.di.module.RealmModule
import phamtuan.flashlightpro.main.BaseFragment
import phamtuan.flashlightpro.main.DoctorAlarmApplication
import javax.inject.Singleton

/**
 * Created by P.Tuan on 10/10/2017.
 */
@Singleton
@Component(modules = arrayOf(RealmModule::class))
interface DatabaseComponent {
    fun inject(app: DoctorAlarmApplication)

    fun inject(presenter: BasePresenter)

}