package phamtuan.doctoralarm.controllers.daily

import phamtuan.doctoralarm.entity.ViewModel

/**
 * Created by P.Tuan on 11/20/2017.
 */
interface DailyContentListener {
    fun OnLoadDataSuccess(listData: ArrayList<ViewModel>)
}