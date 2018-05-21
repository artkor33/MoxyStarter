package com.moxymvp.starter.logger.debug

import android.text.TextUtils
import com.moxymvp.starter.logger.LogPriority
import com.moxymvp.starter.logger.buildFileLogMessage
import timber.log.Timber


/**
 * Created by Artem Korolchuk on 04.10.17.
 * <href a="http://blak-it.com"></href>
 */

class FileLoggingDebugTree : Timber.DebugTree() {


    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == LogPriority.FILE
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        tryLogToFile(tag, t, message)
    }

    private fun tryLogToFile(tag: String?, t: Throwable?, message: String?, vararg args: Any?) {
        val resultMessage = buildFileLogMessage(tag, t, message, args)
        if (!TextUtils.isEmpty(resultMessage)) {
            logToFile(resultMessage)
        }
    }

    private fun logToFile(resultMessage: String) {
        appendLog(resultMessage)
    }
}