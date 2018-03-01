package phamtuan.doctoralarm.controllers.singleintake.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import kotlinx.android.synthetic.main.item_view_drop.view.*
import phamtuan.doctoralarm.R

/**
 * Created by P.Tuan on 11/17/2017.
 */
class SpinerUnitAdapter(context: Context, var array: Array<String>) : BaseAdapter(), SpinnerAdapter {
    var inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = inflater.inflate(R.layout.item_view_drop, p2, false)
        view?.tvValues?.text = array[p0]
        return view
    }

    override fun getItem(p0: Int): Any {
       return array[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return array.size
    }
}