package phamtuan.doctoralarm.controllers.daily.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.ViewGroup
import org.joda.time.LocalDate
import phamtuan.doctoralarm.controllers.daily.fragment.DailyFragmentContent
import phamtuan.doctoralarm.controllers.weekly.fragment.WeeklyFragmentContent
import phamtuan.doctoralarm.customviews.InfiniteViewPagerAdapter
import phamtuan.doctoralarm.utils.DateUtil

/**
 * Created by P.Tuan on 11/16/2017.
 */
class DailyPagerAdapter(var type: Int, var localDate: LocalDate, frm: FragmentManager) : InfiniteViewPagerAdapter<LocalDate>(localDate, frm) {
    companion object {
        @JvmField
        var DAILY = 0

        @JvmField
        var WEEKLY = 1
    }

    override fun getItem(indicator: LocalDate, indext: Int): Fragment {
        when (type) {
            DAILY -> {
                return DailyFragmentContent.newInstace(localDate.plusDays(indext))
            }
            else -> {
                return WeeklyFragmentContent.newInstace(localDate.plusDays(indext))
            }
        }
    }
    override fun getPageTitle(position: Int): CharSequence {
        var dateLocal: LocalDate? = null
        when(type){
            DAILY -> {
                 dateLocal = localDate.plusDays(position - InfiniteViewPagerAdapter.PAGE_CURRENT)

            }
            else -> {
                dateLocal = localDate.plusWeeks(position - InfiniteViewPagerAdapter.PAGE_CURRENT)
            }
        }
        return DateUtil.converDateToString(dateLocal.toDate(),DateUtil.DATE_FORMAT_1)!!
    }

    override fun getItemPosition(`object`: Any?): Int {
          return POSITION_NONE
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }
}