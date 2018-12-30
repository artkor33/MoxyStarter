package com.moxymvp.starter.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.textfield.TextInputLayout


/**
 * Created by Artem Korolchuk on 12/30/18.
 * <href a="http://blak-it.com"></href>
 */

fun TextView.afterTextChanged(action: (text: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action(s?.toString() ?: "")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun TextInputLayout.clearError() {
    error = null
}

fun View.setMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.setMargins(margin, margin, margin, margin)
    requestLayout()
}

fun View.setStartMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.marginStart = margin
    requestLayout()
}

fun View.setEndMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.marginEnd = margin
    requestLayout()
}

fun View.setBottomMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.bottomMargin = margin
    requestLayout()
}

fun View.setTopMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.topMargin = margin
    requestLayout()
}

fun ViewPager.doOnPageSelected(action: (position: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            action.invoke(position)
        }
    })
}

fun ViewPager.doOnPageScrolled(action: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            action(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
        }
    })
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.isVisible() =
        visibility == View.VISIBLE

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleIf(predicate: Boolean, otherwise: Int = View.GONE) {
    if (predicate) {
        visible()
    } else {
        visibility = otherwise
    }
}

fun View.setDelayedOnClickListener(onClick: () -> Unit, delay: Long = 1000) =
        setOnClickListener(object : View.OnClickListener {
            private var lastClickTime = 0L

            override fun onClick(p0: View?) {
                if (System.currentTimeMillis() - lastClickTime > delay) {
                    lastClickTime = System.currentTimeMillis()
                    onClick()
                }
            }
        })

fun View.doOnGlobalLayoutOnce(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            action()
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}