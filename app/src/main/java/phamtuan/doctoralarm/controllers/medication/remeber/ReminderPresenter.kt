package phamtuan.doctoralarm.controllers.medication.remeber

import android.support.v4.app.DialogFragment
import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.entity.Reminder

/**
 * Created by P.Tuan on 11/21/2017.
 */
class ReminderPresenter(var view: DialogFragment, var listener: ReminderPreseneterListener): BasePresenter() {

    fun createRemeber(): Reminder {
        var mRealm = realmHelper.getRealm()
        var remember = Reminder()
        remember.id = realmHelper.getNextId(mRealm, Reminder::class.java)
        mRealm.close()
        return remember
    }
    fun save(remeber: Reminder){
        var mRealm = realmHelper.getRealm()
        realmHelper.addOrUpdateObj(mRealm,remeber)
        mRealm.close()
    }
}