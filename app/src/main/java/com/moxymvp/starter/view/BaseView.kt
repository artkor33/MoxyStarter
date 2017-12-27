package com.moxymvp.starter.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

interface BaseView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun toastShort(msg: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun toastShort(resId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun toastLong(msg: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun toastLong(resId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showNoInternetConnectionError()
}