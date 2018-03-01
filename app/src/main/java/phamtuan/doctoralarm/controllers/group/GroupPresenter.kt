package phamtuan.doctoralarm.controllers.group

import android.content.Intent
import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.controllers.group.CreateGroupActivity.Companion.FLAG_MAIN
import phamtuan.doctoralarm.controllers.group.CreateGroupActivity.Companion.FLAG_MEDICATION
import phamtuan.doctoralarm.controllers.group.CreateGroupActivity.Companion.FLAG_SINGLEINTAKE
import phamtuan.doctoralarm.controllers.main.MainActivity
import phamtuan.doctoralarm.controllers.medication.CreateMedicationActivity
import phamtuan.doctoralarm.controllers.singleintake.CreateSingleIntakeActivity
import phamtuan.doctoralarm.entity.Group

/**
 * Created by P.Tuan on 10/23/2017.
 */
class GroupPresenter(var view: CreateGroupActivity) : BasePresenter() {

    fun addGroup(group: Group, flag: String?) {
        var mRealm = realmHelper.getRealm()
        realmHelper.addOrUpdateObj(mRealm, group)
        mRealm.close()
        if (flag.isNullOrBlank()) {
            view.finish()
        } else {
            when(flag){
                FLAG_SINGLEINTAKE -> {
                    var intent = Intent()
                    intent.putExtra(CreateSingleIntakeActivity.RESULT_GROUP,group)
                    view.setResult(CreateSingleIntakeActivity.RESULT_SINGLEINTAKE_GROUP,intent)
                    view.finish()
                }
                FLAG_MEDICATION -> {
                    var intent = Intent()
                    intent.putExtra(CreateMedicationActivity.RESULT_GROUP,group)
                    view.setResult(CreateMedicationActivity.RESULT_MEDICATION_GROUP,intent)
                    view.finish()
                }
                FLAG_MAIN -> {
                    view.setResult(MainActivity.REQUEST_GROUP)
                    view.finish()
                }
            }
        }
    }

    fun createGroup(): Group {
        var mRealm = realmHelper.getRealm()
        var group = Group()
        group.id = realmHelper.getNextId(mRealm, Group::class.java)
        mRealm.close()
        return group
    }
}