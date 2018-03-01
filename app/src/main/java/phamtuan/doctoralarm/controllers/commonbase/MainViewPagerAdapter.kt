//package phamtuan.doctoralarm.controllers.commonbase
//
//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentManager
//import org.joda.time.LocalDate
//import phamtuan.doctoralarm.controllers.daily.fragment.DailyFragmentContent
//import phamtuan.doctoralarm.controllers.weekly.fragment.WeeklyFragment
//import phamtuan.doctoralarm.customviews.InfiniteViewPagerAdapter
//
///**
// * Created by P.Tuan on 10/10/2017.
// */
//class DailyPagerAdapter(var type: Int, initValue: LocalDate, frm: FragmentManager) : InfiniteViewPagerAdapter<LocalDate>(initValue, frm) {
//    companion object {
//        @JvmField
//        val DAiLY = 0
//        @JvmField
//        val WEEKLY = 1
//    }
//
//    override fun getNextIndicator(): LocalDate {
//        when (type) {
//            DAiLY -> {
//                return mCurrentIndicator!!.plusDays(1)
//            }
//            else -> {
//                return mCurrentIndicator!!.plusMonths(1)
//            }
//        }
//    }
//
//    override fun getPreviousIndicator(): LocalDate {
//        when (type) {
//            DAiLY -> {
//                return mCurrentIndicator!!.minusDays(1)
//            }
//            else -> {
//                return mCurrentIndicator!!.minusMonths(1)
//            }
//        }
//    }
//
//    override fun getItem(indicator: LocalDate): Fragment {
//        when (type) {
//            DAiLY -> {
//                return DailyFragmentContent.newInstace(indicator)
//            }
//            else -> {
//                return WeeklyFragment.newInstace(indicator)
//            }
//        }
//    }
//
//}