package com.moxymvp.starter.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.MvpAppCompatFragment
import com.moxymvp.starter.R
import com.moxymvp.starter.view.BaseView
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BaseFragmentView : MvpAppCompatFragment(), BaseView {
    private lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(getLayoutResID(), container, false)
        unbinder = ButterKnife.bind(this, v)
        setupView(savedInstanceState)
        return v
    }

    abstract fun getLayoutResID(): Int

    abstract fun setupView(savedInstanceState: Bundle?)

    override fun toastShort(msg: String) {
        activity?.toast(msg)
    }

    override fun toastShort(resId: Int) {
        activity?.toast(resId)
    }

    override fun toastLong(msg: String) {
        activity?.longToast(msg)
    }

    override fun toastLong(resId: Int) {
        activity?.longToast(resId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun showNoInternetConnectionError() {
        if (activity == null) {
            return
        }

        val snackBar: Snackbar = Snackbar.make(activity!!.window.decorView.findViewById(android.R.id.content),
                R.string.msg_error_no_internet_connection, Snackbar.LENGTH_LONG)
                .setAction(R.string.settings, { showInternetSettingsScreen() })
        snackBar.show()
    }

    private fun showInternetSettingsScreen() {
        startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
    }
}