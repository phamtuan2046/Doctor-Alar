package phamtuan.doctoralarm.customviews

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by P.Tuan on 11/16/2017.
 */
abstract class InfiniteViewPagerAdapter<T>(initValue: T, frm: FragmentManager) : FragmentPagerAdapter(frm) {
    companion object {
        @JvmField
        var PAGE_COUNT = 300
        @JvmField
        var PAGE_POSITION_LEFT = 0
        @JvmField
        var PAGE_POSITION_CENTER = 1
        @JvmField
        var PAGE_POSITION_RIGHT = 2
        @JvmField
        var PAGE_CURRENT = 150

    }

    var mCurrentIndicator: T? = null

    init {
        mCurrentIndicator = initValue
    }

    override fun getItem(position: Int): Fragment {
       return getItem(mCurrentIndicator!!,position-PAGE_CURRENT)
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        return super.getPageTitle(position)
    }
    /**
     *
     * @param currentIndicator the current value of the indicator.
     * @return a string representation of the current indicator.
     * @see .convertToIndicator
     */
    fun getStringRepresentation(): String {
        return ""
    }

    /**
     * Convert the represented string back to its indicator
     * @param representation the string representation of the current indicator.
     * @return the indicator.
     */
    fun convertToIndicator(representation: String): T {
        return mCurrentIndicator!!
    }
    /**
     *
     * @return the next indicator.
     */
//    abstract fun getNextIndicator(indext: Int): T
//
//    /**
//     *
//     * @return the previous indicator.
//     */
//    abstract fun getPreviousIndicator(indext: Int): T

    /**
     * Instantiates a page.
     * @param indicator the indicator the page should be instantiated with.
     * @return a ViewGroup containing the page layout.
     */
    abstract fun getItem(indicator: T,indext: Int): Fragment
}