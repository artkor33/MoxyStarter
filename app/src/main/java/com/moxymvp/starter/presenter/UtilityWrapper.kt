package com.moxymvp.starter.presenter

import android.content.Context
import com.moxymvp.starter.App
import com.moxymvp.starter.Preferences
import javax.inject.Inject


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

class UtilityWrapper {
    @Inject
    lateinit var applicationContext: Context

    @Inject
    lateinit var preferences: Preferences

    init {
        App.appComponent.inject(this)
    }
}