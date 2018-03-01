package phamtuan.flashlightpro.util

import android.content.Context
import android.content.SharedPreferences

/*
 * StoreData Preferences
 */
class StoreData(internal var context: Context) {

    internal var prefs: SharedPreferences? = null
    internal var editor: SharedPreferences.Editor? = null

    init {
        prefs = this.context.getSharedPreferences("karadacare.Data",
                Context.MODE_PRIVATE)
    }

    fun delete() {
        prefs!!.edit().clear().apply()
    }

    fun getStringValue(key: String): String {
        return prefs!!.getString(key, "")
    }

    fun setStringValue(key: String, value: String) {
        if (editor == null) {
            editor = prefs!!.edit()
        }
        editor!!.putString(key, value)
        editor!!.commit()
    }

    fun getIntValue(key: String): Int {
        return prefs!!.getInt(key, 0)
    }

    fun setIntValue(key: String, value: Int) {
        if (editor == null) {
            editor = prefs!!.edit()
        }
        editor!!.putInt(key, value)
        editor!!.commit()
    }

    fun removeKey(key: String) {
        if (editor == null) {
            editor = prefs!!.edit()
        }
        editor!!.remove(key)
        editor!!.commit()
    }

    fun setBooleanValue(key: String, value: Boolean) {
        if (editor == null) {
            editor = prefs!!.edit()
        }
        editor!!.putBoolean(key, value)
        editor!!.commit()
    }

    fun getBooleanValue(key: String): Boolean {
        return prefs!!.getBoolean(key, false)
    }
}
