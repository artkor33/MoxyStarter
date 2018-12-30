package com.moxymvp.starter.utils

import android.content.res.Resources
import android.util.TypedValue


/**
 * Created by Artem Korolchuk on 12/30/18.
 * <href a="http://blak-it.com"></href>
 */

fun Number.spToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)

fun Number.dpToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)