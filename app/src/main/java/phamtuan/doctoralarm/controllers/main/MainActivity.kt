package phamtuan.doctoralarm.controllers.main

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.icu.text.UnicodeSet
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import phamtuan.doctoralarm.R
import phamtuan.doctoralarm.controllers.main.adaper.AddMenuAdapter
import phamtuan.doctoralarm.controllers.appointment.CreateAppointmentActivity
import phamtuan.doctoralarm.base.BaseViewOnListener
import phamtuan.doctoralarm.controllers.daily.fragment.DailyFragment
import phamtuan.doctoralarm.controllers.doctor.CreateDoctorActivity
import phamtuan.doctoralarm.controllers.doctor.fragment.FragmentListDoctor
import phamtuan.doctoralarm.controllers.group.CreateGroupActivity
import phamtuan.doctoralarm.controllers.group.fragment.FragmentGroupList
import phamtuan.doctoralarm.controllers.listeners.BaseItemClickListener
import phamtuan.doctoralarm.controllers.medication.CreateMedicationActivity
import phamtuan.doctoralarm.controllers.singleintake.CreateSingleIntakeActivity
import phamtuan.doctoralarm.controllers.weekly.fragment.WeeklyFragment
import phamtuan.doctoralarm.models.MenuItemAdd
import phamtuan.flashlightpro.main.BaseActivity
import java.util.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, Animation.AnimationListener, BaseItemClickListener {
    companion object {
        var REQUEST_MEDICATION = 0
        var REQUEST_SINGLEINTAKE = 1
        var REQUEST_APPOINTMENT = 2
        var REQUEST_GROUP = 3
        var REQUEST_DOCTOR = 4


    }

    private var menuAdds = ArrayList<MenuItemAdd>()
    private var menuAddAdapter: AddMenuAdapter? = null

    var currentView = 0

    var listener: BaseViewOnListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navViewMenu.setNavigationItemSelectedListener(this)
        initview()
        initMenuAdd()
        replaceFragment(R.id.contenMain, DailyFragment.newInstance(), false)

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun OnItemClick(view: View, position: Int) {
        lnMenuAdd.setVisibility(View.INVISIBLE)
        btnAdd.setImageResource(R.drawable.img_add1)
        mainViewMenu.setBackgroundColor(resources.getColor(R.color.transparent))
        when (position) {
            0 -> {
                var i = CreateMedicationActivity.createIntentAddMedication(this)
                startActivityForResult(i, REQUEST_MEDICATION)
            }
            1 -> {
                var i = CreateSingleIntakeActivity.createIntentAdd(this)
                startActivityForResult(i, REQUEST_SINGLEINTAKE)
            }
            2 -> {
                var i = CreateAppointmentActivity.createIntentAddAppointment(this)
                startActivityForResult(i, REQUEST_APPOINTMENT)
            }
            3 -> {
                var i = CreateGroupActivity.createIntentAddMain(this)
                startActivityForResult(i, REQUEST_GROUP)
            }
            4 -> {
                var i = CreateDoctorActivity.createIntentAddDoctor(this)
                startActivityForResult(i, REQUEST_DOCTOR)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var fragment = supportFragmentManager.findFragmentById(R.id.contenMain)
        when (requestCode) {
            REQUEST_MEDICATION -> {
                if (currentView == 0 && fragment != null) {
                    fragment as DailyFragment
                    fragment.reloadView()
                }
            }
            REQUEST_SINGLEINTAKE -> {
                if (currentView == 1 && fragment != null) {
                    fragment as WeeklyFragment
                    fragment.reloadView()
                }
            }
            REQUEST_APPOINTMENT -> {
                if (currentView == 2 && fragment != null) {
                    fragment as FragmentListDoctor
                    fragment.reload()
                }
            }
            REQUEST_GROUP -> {
                if (currentView == 0 && fragment != null) {

                }
            }
            REQUEST_DOCTOR -> {
                if (currentView == 0 && fragment != null) {

                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navDaily -> {
                settitle("Daily Timeline")
                if (currentView != 0) replaceFragment(R.id.contenMain, DailyFragment.newInstance(), false)
                currentView = 0
            }
            R.id.navWeekly -> {
                settitle("Weekly Timeline")
                if (currentView != 1) replaceFragment(R.id.contenMain, WeeklyFragment.newInstance(), false)
                currentView = 1
            }
            R.id.navDoctor -> {
                settitle("Doctor List")
                if (currentView != 3) replaceFragment(R.id.contenMain, FragmentListDoctor.newInstance(), false)
                currentView = 3
            }
            R.id.navGroup -> {
                settitle("Group List")
                if (currentView != 4) replaceFragment(R.id.contenMain, FragmentGroupList.newInstance(), false)
                currentView = 4
            }
            R.id.navInventory -> {
                settitle("Inventory")

            }
            R.id.navFilter -> {

            }
            R.id.navSearch -> {

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnAdd -> {
                setShowMenu()
            }
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {

    }

    override fun onAnimationStart(p0: Animation?) {

    }

    private fun setShowMenu() {
        if (lnMenuAdd.visibility == View.VISIBLE) {
            hideMenu()
        } else {
            showMenu()
        }
    }

    fun settitle(title: String) {
        titleToolbar.text = title
    }

    private fun showMenu() {
        val animSide = AnimationUtils.loadAnimation(applicationContext,
                R.anim.slide_bottom_to_top)
        lnMenuAdd.setLayoutAnimationListener(this)
        lnMenuAdd.setVisibility(View.VISIBLE)
        lnMenuAdd.startAnimation(animSide)

        val animRotate = AnimationUtils.loadAnimation(applicationContext,
                R.anim.rotate)
        animRotate.setAnimationListener(this)
        btnAdd.startAnimation(animRotate)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnAdd.setImageResource(R.drawable.img_add2)
        }
        val lp = this.window.attributes
        lp.dimAmount = 0.7f
        mainViewMenu.setBackgroundColor(Color.parseColor("#99000000"))
//        disable(mainView)
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun hideMenu() {
        val animSide = AnimationUtils.loadAnimation(applicationContext,
                R.anim.slide_top_to_bottom)
        lnMenuAdd.setLayoutAnimationListener(this)
        lnMenuAdd.setVisibility(View.INVISIBLE)
        lnMenuAdd.startAnimation(animSide)

        val animRotate = AnimationUtils.loadAnimation(applicationContext,
                R.anim.rotate2)
        animRotate.setAnimationListener(this)
        btnAdd.startAnimation(animRotate)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnAdd.setImageResource(R.drawable.img_add1)
        }

        mainViewMenu.setBackground(null)
//        enable(mainView)
    }

    private fun initview() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        btnToday.setOnClickListener { listener?.OnTodayClick() }
    }


    private fun initMenuAdd() {
        menuAdds.add(MenuItemAdd("Add Medication", R.drawable.img_medication))
        menuAdds.add(MenuItemAdd("Add Single Intake", R.drawable.img_single_in_take))
        menuAdds.add(MenuItemAdd("Add Appointment", R.drawable.img_appointment))
        menuAdds.add(MenuItemAdd("Add Group", R.drawable.img_group))
        menuAdds.add(MenuItemAdd("Add Doctor", R.drawable.img_add_doctor))
        menuAddAdapter = AddMenuAdapter(this, menuAdds, this)
        rcvMenuAdd.adapter = menuAddAdapter
        btnAdd.setOnClickListener(this)
    }

}
