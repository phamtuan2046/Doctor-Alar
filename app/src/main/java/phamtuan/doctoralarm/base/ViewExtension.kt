package com.ominext.basekotlin.components.base.view

import android.app.Activity
import android.support.v4.app.Fragment
import phamtuan.flashlightpro.main.BaseActivity
import phamtuan.flashlightpro.main.DoctorAlarmApplication

/**
 * Created by Ominext on 6/29/2017.
 */

val Activity.app: DoctorAlarmApplication
    get() {
        return application as DoctorAlarmApplication
    }

val Fragment.app: DoctorAlarmApplication
    get() {
        return activity.application as DoctorAlarmApplication
    }

val Fragment.activityParent: BaseActivity?
    get() {
        return activity as? BaseActivity
    }

