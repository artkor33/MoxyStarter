package com.moxymvp.starter.utils

import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF


/**
 * Created by Artem Korolchuk on 12/30/18.
 * <href a="http://blak-it.com"></href>
 */

fun Paint.getTextBounds(text: String): Rect {
    val rect = Rect()
    getTextBounds(text, 0, text.length, rect)
    return rect
}

fun Paint.getTextHeight(text: String): Int {
    val rect = getTextBounds(text)
    return rect.height()
}

fun Paint.getTextWidth(text: String): Int {
    return measureText(text).toInt()
}

fun RectF.copyShrinked(value: Float) =
        RectF(this.left + value,
                this.top + value,
                this.right - value,
                this.bottom - value)

fun Rect.shrink(value: Int) {
    this.left += value
    this.top += value
    this.right -= value
    this.bottom -= value
}


fun screenWidth() = Resources.getSystem().displayMetrics.widthPixels

fun screenHeight() = Resources.getSystem().displayMetrics.heightPixels

fun statusBarHeight(): Int {
    val myResources = Resources.getSystem()
    val idStatusBarHeight = myResources.getIdentifier("status_bar_height", "dimen", "android")
    return myResources.getDimensionPixelSize(idStatusBarHeight)
}