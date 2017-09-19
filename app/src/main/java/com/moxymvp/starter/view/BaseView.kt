package com.moxymvp.starter.view

import com.arellomobile.mvp.MvpView


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

interface BaseView : MvpView {
    fun toastShort(msg: String)
    fun toastShort(resId: Int)
    fun toastLong(msg: String)
    fun toastLong(resId: Int)
}