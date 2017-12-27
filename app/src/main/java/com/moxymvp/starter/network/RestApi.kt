package com.getmeet.android.network

import android.content.Context
import com.google.gson.Gson
import com.moxymvp.starter.Consts
import com.moxymvp.starter.Preferences
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Artem Korolchuk on 18.12.2017.
 * <href a="http://blak-it.com"></href>
 */

class RestApi @Inject constructor(context: Context, val preferences: Preferences) {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    private lateinit var retrofit: Retrofit

    init {
        invalidate()
    }

    fun invalidate() {
        initRetrofit()
        initServices()
    }

    private fun initRetrofit() {
        val loggingInterceptor = HttpLoggingInterceptor({ it -> Timber.tag(Consts.LogTag.HTTP).d(it) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(RequestInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()

        retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl() Put your base API url here
                .build()
    }

    private fun initServices() {
    }

    inner class RequestInterceptor : okhttp3.Interceptor {
        override fun intercept(chain: okhttp3.Interceptor.Chain?): Response =
                chain!!.proceed(chain.request()
                        .newBuilder()
                        .build())

    }
}