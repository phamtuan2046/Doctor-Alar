package phamtuan.flashlightpro.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import phamtuan.doctoralarm.base.BaseViewOnListener
import phamtuan.doctoralarm.controllers.main.MainActivity


/**
 * Created by P.Tuan on 6/28/2017.
 */

abstract class BaseFragment : Fragment(), BaseViewOnListener {
    var mainView: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = activity as MainActivity
        mainView?.listener = this
    }


    protected fun addFragment(fragment: Fragment, isBackStack: Boolean) {
//        mainView?.addFragment(fragment,isBackStack)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

}
