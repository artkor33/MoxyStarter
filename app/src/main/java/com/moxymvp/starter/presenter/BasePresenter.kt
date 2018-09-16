package com.moxymvp.starter.presenter

import android.content.Context
import android.text.TextUtils
import com.arellomobile.mvp.MvpPresenter
import com.getmeet.android.network.error_handling.ApiErrorListener
import com.getmeet.android.network.error_handling.HttpError
import com.moxymvp.starter.Preferences
import com.moxymvp.starter.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>(), ApiErrorListener {

    private var utilityWrapper: UtilityWrapper = UtilityWrapper()
    val subscription: CompositeDisposable by lazy { CompositeDisposable() }

    val realm: Realm = Realm.getDefaultInstance()
    val applicationContext: Context
        get() = utilityWrapper.applicationContext

    val preferences: Preferences
        get() = utilityWrapper.preferences

    override fun onDestroy() {
        realm.close()
        subscription.dispose()
        super.onDestroy()
    }

    override fun onHttpErrors(errors: Collection<HttpError>) {
        var msg = ""
        errors.map { it.message }
                .forEach {
                    msg += it
                }

        if (!TextUtils.isEmpty(msg)) {
            viewState.toastLong(msg)
        }
    }

    override fun onHttpError(error: HttpError) {
        if (!TextUtils.isEmpty(error.message)) {
            viewState.toastShort(error.message!!)
        }
    }

    override fun onNoInternetConnection() {
        viewState.showNoInternetConnectionError()
    }

    override fun onGenericError(t: Throwable?) {
        if (t != null && t.localizedMessage != null) {
            viewState.toastLong(t.localizedMessage)
        }
    }
}