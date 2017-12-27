package com.getmeet.android.network.error_handling

import android.content.Context
import com.moxymvp.starter.R
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import java.net.UnknownHostException


/**
 * Created by Artem Korolchuk on 19.12.2017.
 * <href a="http://blak-it.com"></href>
 */

fun handleNetworkError(context: Context, t: Throwable?, apiErrorListener: ApiErrorListener?) {
    when (t) {
        is HttpException -> handleHttpException(context, t, apiErrorListener)
        is UnknownHostException -> handleNoInternetConnection(t, apiErrorListener)
        else -> handleGenericError(t, apiErrorListener)
    }
}

fun handleHttpException(context: Context, e: HttpException?, apiErrorListener: ApiErrorListener?) {
    if (e == null || processCommonNetworkError(context, e, apiErrorListener)) {
        return
    }

    val errorJson = e.response().errorBody()?.string()
    val jsonObj = JSONObject(errorJson)

    if (jsonObj.has("errors")) {
        val httpErrors = ArrayList<HttpError>()
        val errors = jsonObj.getJSONObject("errors")
        errors.keys().forEach {
            httpErrors.addAll(parseFieldErrors(errors.getJSONArray(it)))
        }
        apiErrorListener?.onHttpErrors(httpErrors)
    } else if (jsonObj.has("error")) {
        val httpError = HttpError(e.response().code().toString(), jsonObj.getString("error"))
        apiErrorListener?.onHttpError(httpError)
    }
}

fun processCommonNetworkError(context: Context, e: HttpException?, apiErrorListener: ApiErrorListener?): Boolean {
    if (e?.code() == 500) {
        apiErrorListener?.onHttpError(HttpError("500", context.getString(R.string.network_error_server)))
        return true
    }

    if (e?.code() == 401) {
        apiErrorListener?.onHttpError(HttpError("401", context.getString(R.string.network_error_unauthorized)))
        return true
    }
    return false
}

fun parseFieldErrors(errors: JSONArray): Collection<HttpError> {
    val list = ArrayList<HttpError>()
    (0 until errors.length())
            .map { errors.getJSONObject(it) }
            .mapTo(list) { HttpError(it.getString("code"), it.getString("message")) }
    return list
}

fun handleNoInternetConnection(e: UnknownHostException, apiErrorListener: ApiErrorListener?) {
    apiErrorListener?.onNoInternetConnection()
}

fun handleGenericError(t: Throwable?, apiErrorListener: ApiErrorListener?) {
    apiErrorListener?.onGenericError(t)
}
