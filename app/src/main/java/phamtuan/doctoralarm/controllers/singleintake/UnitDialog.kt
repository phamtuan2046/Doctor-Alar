package phamtuan.doctoralarm.controllers.singleintake

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.dialog_unit.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.singleintake.adapter.SpinerUnitAdapter


@Suppress("UNREACHABLE_CODE")
/**
 * Created by P.Tuan on 11/17/2017.
 */
class UnitDialog : DialogFragment() {
    lateinit var mContext: Context
    lateinit var mAdapter: SpinerUnitAdapter
    var arrUnit: Array<String>? = null
    var arrValues: Array<String>? = null
    var values = "1"
    var currentUnit = "Pill"
    lateinit var listener: DialogUnitListener

    companion object {
        fun newIntacnce(context: Context, listener: DialogUnitListener): DialogFragment {
            var dialog = UnitDialog()
            dialog.mContext = context
            dialog.listener = listener
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.dialog_unit, container, false)
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
        initview()
        arrUnit = this.resources.getStringArray(R.array.spinner_dosage)
        arrValues = this.resources.getStringArray(R.array.default_value)
        result.setText(values)
        mAdapter = SpinerUnitAdapter(context, arrUnit!!)
        spUnit?.adapter = mAdapter
        spUnit.setSelection(0)
        spUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                values = arrValues!![p2]
                currentUnit = arrUnit!![p2]
                result.setText(values)
            }
        }
    }

    fun initview() {
        btnBack.setOnClickListener {
            listener.OnDismisDialog()
            dismiss()
        }
        btnDone.setOnClickListener {
            values = result.text.toString()
            listener.OnValuseSuccess(values.toFloat(), currentUnit)
            dismiss()}

        btnSub.setOnClickListener {
            if(!result.text.toString().isNullOrEmpty())values = result.text.toString()
            values = subValue(unitDosage(currentUnit, values.toFloat(), Operator.minus).toString())
            if(values.toFloat() < 0.0){
                values = "0"
            }else{
                result.setText(values)
            }

        }
        btnAdd.setOnClickListener {
            if(!result.text.toString().isNullOrEmpty())values = result.text.toString()
            values = subValue(unitDosage(currentUnit, values.toFloat(), Operator.Plus).toString())
            result.setText(values)
        }
    }

    fun unitDosage(unit: String, input: Float, operator: Operator): Float {
        var output = input
        if (operator == Operator.Plus) {
            when (unit) {
                "Pill" -> {
                    output += 1f
                }
                "Mg" -> {
                    output += 50f
                }
                "Gr" -> {
                    output += 0.5f
                }
                "Ml" -> {
                    output += 50f
                }
                "Ltr" -> {
                    output += 0.5f
                }
                "Oz" -> {
                    output += 0.5f
                }
                "Patch" -> {
                    output += 1f
                }
                "Vial" -> {
                    output += 1f
                }
                "Unit" -> {
                    output += 1f
                }
            }
        } else {
            when (unit) {
                "Pill" -> {
                    output -= 1f
                }
                "Mg" -> {
                    output -= 50f
                }
                "Gr" -> {
                    output -= 0.5f
                }
                "Ml" -> {
                    output -= 50f
                }
                "Ltr" -> {
                    output -= 0.5f
                }
                "Oz" -> {
                    output -= 0.5f
                }
                "Patch" -> {
                    output -= 1f
                }
                "Vial" -> {
                    output -= 1f
                }
                "Unit" -> {
                    output -= 1f
                }
            }
        }
        return output
    }

    enum class Operator {
        Plus,
        minus
    }

    fun subValue(values: String): String {
        if (values.contains(".0")) {
            return values.replace(".0","")
        }else{
            return values
        }
    }
}
