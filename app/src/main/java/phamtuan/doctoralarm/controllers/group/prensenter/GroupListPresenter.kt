package phamtuan.doctoralarm.controllers.group.prensenter

import android.app.Fragment
import com.ominext.basekotlin.components.base.presenter.BasePresenter
import phamtuan.doctoralarm.controllers.group.fragment.FragmentGroupList
import phamtuan.doctoralarm.controllers.group.listener.GroupListIteracter
import phamtuan.doctoralarm.entity.Group

/**
 * Created by P.Tuan on 11/28/2017.
 */
class GroupListPresenter(view: FragmentGroupList, var listener: GroupListIteracter) : BasePresenter() {
    fun getAllGroup() {
        var mRealm = realmHelper.getRealm()
        var groups = realmHelper.findAll(mRealm, Group::class.java)
        mRealm.close()
        if (groups != null) listener.OnLoadGroupSuccess(groups)
    }
}