package phamtuan.doctoralarm.controllers.medication.inventory

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_inventory.*
import phamtuan.doctoralarm.R
import phamtuan.flashlightpro.main.BaseActivity

/**
 * Created by P.Tuan on 11/22/2017.
 */
class InventoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory)
        inittoolbar()
    }
    private fun inittoolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.img_back)
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}