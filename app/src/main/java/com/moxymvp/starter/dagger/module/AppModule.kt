package com.moxymvp.starter.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

@Module
class AppModule(private val applicationContext: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext() = applicationContext
}