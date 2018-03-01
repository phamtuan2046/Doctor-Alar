package phamtuan.doctoralarm.controllers.dialog

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.option_dialog.*
import phamtuan.doctoralarm.R

/**
 * Created by P.Tuan on 11/27/2017.
 */
class OptionDialog : DialogFragment() {

    companion object {
        fun newInstance():OptionDialog{
            var dialog = OptionDialog()
            return dialog
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.option_dialog, container, false)
        val displayRectangle = Rect()
        dialog.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        view?.minimumWidth = (displayRectangle.width() * 0.98f).toInt()
        view?.minimumHeight = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack.setOnClickListener { dismiss() }
        btnEdit.setOnClickListener {  }
        btnSkip.setOnClickListener {  }
        btnTakenow.setOnClickListener {  }
        btnReset.setOnClickListener {  }
    }
}