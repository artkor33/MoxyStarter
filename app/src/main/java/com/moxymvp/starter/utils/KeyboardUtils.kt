package com.moxymvp.starter.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


/**
 * Created by Artem Korolchuk on 12/30/18.
 * <href a="http://blak-it.com"></href>
 */

fun hideSoftKeyboard(activity: Activity?) {
    if (activity == null) {
        return
    }
    val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken, 0)
}

fun listenTouchesToHideKeyboard(activity: Activity) {
    tryToHideKeyboard(activity, activity.window.decorView)
}

fun tryToHideKeyboard(activity: Activity, view: View) {
    // Set up touch listener for non-text box views to hide keyboard.
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            hideSoftKeyboard(activity);
            false
        }
    }

    //If a layout container, iterate over children and seed recursion.
    if (view is ViewGroup) {
        (0 until view.childCount)
                .map { view.getChildAt(it) }
                .forEach { tryToHideKeyboard(activity, it) }
    }
}

fun showKeyboard(context: Context?, editText: EditText) {
    editText.requestFocus()
    editText.postDelayed({
        val keyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.showSoftInput(editText, 0)
    }, 200)
}