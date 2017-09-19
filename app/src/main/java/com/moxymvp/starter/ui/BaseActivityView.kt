package com.moxymvp.starter.ui

import android.os.Bundle
import android.view.MenuItem
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.moxymvp.starter.view.BaseView
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BaseActivityView : MvpAppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResID())
        ButterKnife.bind(this)
        setupView(savedInstanceState)
    }

    fun hideBackButton() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false) // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false) // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false) // remove the icon
        }
    }

    fun showBackButton() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true) // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true) // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true) // remove the icon
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun getLayoutResID(): Int

    abstract fun setupView(savedInstanceState: Bundle?)


    override fun toastShort(msg: String) {
        toast(msg)
    }

    override fun toastShort(resId: Int) {
        toast(resId)
    }

    override fun toastLong(msg: String) {
        longToast(msg)
    }

    override fun toastLong(resId: Int) {
        longToast(resId)
    }
}