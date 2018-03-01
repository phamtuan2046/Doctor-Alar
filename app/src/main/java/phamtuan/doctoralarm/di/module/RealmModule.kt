package phamtuan.doctoralarm.di.module

import com.ominext.basekotlin.components.base.presenter.BasePresenter
import dagger.Module
import dagger.Provides
import phamtuan.fingerdrawerx.database.RealmHelper
import phamtuan.flashlightpro.main.BaseFragment
import javax.inject.Singleton

/**
 * Created by P.Tuan on 10/18/2017.
 */
@Module
open class RealmModule(presenter: BasePresenter) {
//    lateinit var presenter: BasePresenter
//
//    init {
//        this.presenter = presenter
//    }

    @Provides
    fun provideRealmHelper(): RealmHelper {
        return RealmHelper()
    }
}