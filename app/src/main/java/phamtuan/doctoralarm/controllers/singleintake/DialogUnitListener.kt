package phamtuan.doctoralarm.controllers.singleintake

/**
 * Created by P.Tuan on 11/20/2017.
 */
interface DialogUnitListener {
    fun OnValuseSuccess(values: Float,unit: String)

    fun OnDismisDialog()
}