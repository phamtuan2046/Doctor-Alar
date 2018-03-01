package com.ominext.basekotlin.components.base.presenter

import phamtuan.doctoralarm.di.component.DaggerDatabaseComponent
import phamtuan.doctoralarm.di.component.DatabaseComponent
import phamtuan.doctoralarm.di.module.RealmModule
import phamtuan.fingerdrawerx.database.RealmHelper
import javax.inject.Inject


/**
 * Created by TuanPD on 10/10/17.
 */
open abstract class BasePresenter {
    lateinit var databaseComponent: DatabaseComponent

    @Inject
    lateinit var realmHelper: RealmHelper

    constructor() {
        databaseComponent = DaggerDatabaseComponent.builder()
                .realmModule(RealmModule(this))
                .build()
        databaseComponent.inject(this)
    }

}