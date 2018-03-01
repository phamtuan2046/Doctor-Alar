package phamtuan.doctoralarm.controllers.weekly.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_day_week.*
import org.joda.time.LocalDate
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.daily.adapter.DailyPagerAdapter

/**
 * Created by P.Tuan on 11/16/2017.
 */
class WeeklyFragment: Fragment(){
     var mAdapter: DailyPagerAdapter? = null

    companion object {
        fun newInstance(): Fragment {
            var fragment = WeeklyFragment()
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_day_week,container,false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initview()
    }
    fun initview(){
        mAdapter = DailyPagerAdapter(DailyPagerAdapter.DAILY, LocalDate.now(), fragmentManager)
        mainViewPager.adapter = mAdapter
        mainViewPager.currentItem = 150
        daylyTabstrip.drawFullUnderline = true
    }
    fun reloadView(){
        mAdapter= null
        mAdapter = DailyPagerAdapter(DailyPagerAdapter.DAILY, LocalDate.now(), fragmentManager)
        mainViewPager.adapter = mAdapter
        mainViewPager.currentItem = 150
        daylyTabstrip.drawFullUnderline = true
    }
}