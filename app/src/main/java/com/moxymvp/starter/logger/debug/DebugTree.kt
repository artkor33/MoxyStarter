package com.moxymvp.starter.logger.debug

import android.util.Log
import com.moxymvp.starter.logger.LogPriority
import timber.log.Timber


/**
 * Created by Artem Korolchuk on 04.10.17.
 * <href a="http://blak-it.com"></href>
 */

class DebugTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == LogPriority.FILE) {
            super.log(Log.DEBUG, tag, message, t)
        } else {
            super.log(priority, tag, message, t)
        }
    }
}