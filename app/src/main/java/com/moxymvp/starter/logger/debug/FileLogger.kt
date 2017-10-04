package com.moxymvp.starter.logger.debug

import com.moxymvp.starter.BuildConfig
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


/**
 * Created by Artem Korolchuk on 04.10.17.
 * <href a="http://blak-it.com"></href>
 */

// For file logging you should provide WRITE_EXTERNAL_STORAGE permission

var DEFAULT_PATH = "sdcard/${BuildConfig.APPLICATION_ID.replace(".", "_")}.txt"

fun appendLog(message: String, filePath: String = DEFAULT_PATH) {
    try {
        val logFile = File(filePath)
        if (!logFile.exists()) {
            logFile.createNewFile()

        }
        val bufWriter = BufferedWriter(FileWriter(logFile, true))
        bufWriter.append(message)
        bufWriter.newLine()
        bufWriter.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

