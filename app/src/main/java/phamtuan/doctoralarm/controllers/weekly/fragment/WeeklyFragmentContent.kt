package phamtuan.doctoralarm.controllers.weekly.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_daily.*
import org.joda.time.LocalDate
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.daily.fragment.DailyFragmentContent
import phamtuan.doctoralarm.utils.DateUtil
import phamtuan.flashlightpro.main.BaseFragment

/**
 * Created by P.Tuan on 11/16/2017.
 */
class WeeklyFragmentContent: BaseFragment(){
    var mDate: LocalDate? = null

    companion object {
        fun newInstace(localDate: LocalDate): Fragment {
            var fragment = WeeklyFragmentContent()
            fragment.mDate = localDate
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_daily, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun OnTodayClick() {
        
    }
}