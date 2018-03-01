package phamtuan.doctoralarm.controllers.group.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_group.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.group.adapter.GroupListAdapter
import phamtuan.doctoralarm.controllers.group.listener.GroupListIteracter
import phamtuan.doctoralarm.controllers.group.prensenter.GroupListPresenter
import phamtuan.doctoralarm.entity.Group
import phamtuan.flashlightpro.main.BaseFragment

/**
 * Created by P.Tuan on 11/28/2017.
 */
class FragmentGroupList: BaseFragment(), GroupListIteracter, GroupListAdapter.Interactor {

    var prenseter: GroupListPresenter? = null
    var groups = ArrayList<Group>()
    var groupAdapter: GroupListAdapter? = null

    override fun OnTodayClick() {

    }
    companion object {
        fun newInstance():FragmentGroupList{
            var fragment = FragmentGroupList()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prenseter = GroupListPresenter(this,this)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_list_group,container,false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initview()
        prenseter?.getAllGroup()
    }

    override fun OnLoadGroupSuccess(listGroup: ArrayList<Group>) {
        groups.clear()
        groups.addAll(listGroup)
        groupAdapter?.notifyDataSetChanged()
    }

    override fun OnEdit(index: Int) {

    }

    fun initview(){
        groupAdapter = GroupListAdapter(context,groups,this)
        rcvGroups.adapter = groupAdapter
    }
}