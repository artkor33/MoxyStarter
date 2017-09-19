package com.moxymvp.starter.ui

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.moxymvp.starter.R
import com.moxymvp.starter.presenter.MainPresenter
import com.moxymvp.starter.view.MainView

class MainActivity : BaseActivityView(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun getLayoutResID() = R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {
    }
}
