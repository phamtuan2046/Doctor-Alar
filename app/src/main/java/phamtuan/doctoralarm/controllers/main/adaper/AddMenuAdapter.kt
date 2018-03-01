package phamtuan.doctoralarm.controllers.main.adaper

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.models.MenuItemAdd
import kotlinx.android.synthetic.main.item_menu_add.view.*
import phamtuan.doctoralarm.controllers.listeners.BaseItemClickListener

/**
 * Created by P.Tuan on 10/11/2017.
 */
class AddMenuAdapter : RecyclerView.Adapter<AddMenuAdapter.ItemMenuViewHolder> {
    private var menuItems: ArrayList<MenuItemAdd>? = null
    private var mContext: Context? = null
    private var mListener: BaseItemClickListener? = null

    constructor(context: Context, menus: ArrayList<MenuItemAdd>,listener: BaseItemClickListener) : super() {
        this.mContext = context
        this.menuItems = menus
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemMenuViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_menu_add, parent, false)
        return ItemMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemMenuViewHolder?, position: Int) {
        holder?.itemView?.itemImgAvatar?.setImageResource(menuItems!![position].image!!)
        holder?.itemView?.tvNameMenu?.text = menuItems!![position].name
        holder?.itemView?.setOnClickListener( {view ->
            mListener?.OnItemClick(view,position)
        })
    }

    override fun getItemCount(): Int {
        return menuItems!!.size
    }

    class ItemMenuViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View?) : super(itemView)
    }
}