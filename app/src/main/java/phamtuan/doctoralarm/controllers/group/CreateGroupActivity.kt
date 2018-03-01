package phamtuan.doctoralarm.controllers.group

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.create_group_activity.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.common.Constant
import phamtuan.doctoralarm.entity.Group
import phamtuan.doctoralarm.utils.KeyboardUtil
import phamtuan.flashlightpro.main.BaseActivity

/**
 * Created by P.Tuan on 10/22/2017.
 */
class CreateGroupActivity : BaseActivity() {
    lateinit var presenter: GroupPresenter
    var FLAG: String? = null

    companion object {
        @JvmField val FLAG_SINGLEINTAKE = "create_group_from_singleintake"
        @JvmField val FLAG_MEDICATION = "create_group_from_medication"
        @JvmField val FLAG_MAIN = "create_group_from_main"
        fun createIntentIntake(context: Context): Intent {
            var intent = Intent(context,CreateGroupActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_SINGLEINTAKE)
            return intent
        }
        fun createIntentMedication(context: Context): Intent {
            var intent = Intent(context,CreateGroupActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_MEDICATION)
            return intent
        }

        fun createIntentAddMain(context: Context): Intent {
            var intent = Intent(context,CreateGroupActivity::class.java)
            intent.putExtra(Constant.FLAG,FLAG_MAIN)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_group_activity)
        initview()
        presenter = GroupPresenter(this)
        getdata()
        btnCreateGoup.setOnClickListener({ view ->
            createGroup()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        KeyboardUtil.hideSoftKeyboard(this)
        finish()
        return true
    }

    fun initview() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.img_back)
    }

    fun getdata() {
        var intenData = intent
        if (intenData != null) FLAG = intenData.getStringExtra(Constant.FLAG)
    }

    fun createGroup() {
        if (edGroup.text.toString().isNotEmpty()) {
            edGroup.clearFocus()
            var group = presenter.createGroup()
            group.group_name = edGroup.text.toString()
            presenter.addGroup(group, FLAG)
        } else {
            Toast.makeText(this, "Group not must empty", Toast.LENGTH_LONG)
        }
    }
}