package phamtuan.doctoralarm.controllers.listeners

import android.view.View

/**
 * Created by P.Tuan on 10/11/2017.
 */
interface BaseItemClickListener {
    fun OnItemClick(view: View,position: Int)
}