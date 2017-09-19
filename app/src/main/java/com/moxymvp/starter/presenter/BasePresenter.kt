package com.moxymvp.starter.presenter

import com.arellomobile.mvp.MvpPresenter
import com.moxymvp.starter.view.BaseView
import io.realm.Realm


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>() {

    private var utilityWrapper: UtilityWrapper = UtilityWrapper()

    val realm: Realm = Realm.getDefaultInstance()

    fun applicationContext() = utilityWrapper.applicationContext

    fun preferences() = utilityWrapper.preferences

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}