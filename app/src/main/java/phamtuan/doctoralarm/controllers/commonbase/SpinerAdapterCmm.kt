package phamtuan.doctoralarm.controllers.commonbase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import phamtuan.doctoralarm.R

/**
 * Created by P.Tuan on 10/13/2017.
 */
abstract class SpinerAdapterCmm: BaseAdapter,SpinnerAdapter {
    protected abstract fun initView(view: View,position: Int)
    protected abstract fun initViewDrop(view: View,position: Int)
//    protected abstract fun initContext(): Context
//    protected abstract fun initData(): List<Any>

    private var mContext: Context? = null
    private var mLayoutinfater: LayoutInflater? = null
    private var listObject: List<Any>? = null

    constructor(mContext: Context?, data: List<Any>) : super() {
        this.mContext = mContext
        this.listObject = data
        mLayoutinfater = LayoutInflater.from(mContext)
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        var view = mLayoutinfater!!.inflate(R.layout.item_spiner_default,parent,false)
        initView(view,position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = mLayoutinfater!!.inflate(R.layout.item_view_drop,parent,false)
        initViewDrop(view,position)
        return view
    }
    override fun getItem(p0: Int): Any {
        return listObject!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listObject!!.size
    }
}