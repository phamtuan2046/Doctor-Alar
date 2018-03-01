package phamtuan.flashlightpro.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by P.Tuan on 8/14/2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    public fun replaceFragment(id: Int, fragment: Fragment, isBackstack: Boolean) {
        var fragmentManager = supportFragmentManager;
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(id, fragment)
        if (isBackstack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }
        fragmentTransaction.commit()
    }

    public fun addFragment(id: Int, fragment: Fragment, isBackstack: Boolean) {
        var fragmentManager = supportFragmentManager;
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(id, fragment)
        if (isBackstack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }
        fragmentTransaction.commit()
    }
}