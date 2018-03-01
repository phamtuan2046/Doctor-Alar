package phamtuan.doctoralarm.utils

import android.util.Patterns

/**
 * Created by P.Tuan on 10/18/2017.
 */

object Util {
    fun isValidEmaillId(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }
}
