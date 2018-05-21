package com.moxymvp.starter.logger.release

import android.util.Log
import timber.log.Timber


/**
 * Created by Artem Korolchuk on 04.10.17.
 * <href a="http://blak-it.com"></href>
 */

class ReleaseTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int) = priority == Log.ERROR || priority == Log.INFO

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR && t != null) {
            //i.e.You can log exceptions via Crashlytics or sth else
        } else {
            Log.i(tag, message)
        }
    }
}