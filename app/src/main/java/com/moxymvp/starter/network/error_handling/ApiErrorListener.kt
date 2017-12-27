package com.getmeet.android.network.error_handling


/**
 * Created by Artem Korolchuk on 19.12.2017.
 * <href a="http://blak-it.com"></href>
 */

interface ApiErrorListener {
    fun onHttpErrors(errors: Collection<HttpError>)

    fun onHttpError(error: HttpError)

    fun onNoInternetConnection()

    fun onGenericError(t: Throwable?)
}