package com.moxymvp.starter.dagger.module

import android.content.Context
import android.preference.PreferenceManager
import com.moxymvp.starter.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

@Module
class StorageModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun providePreferences() = Preferences(PreferenceManager.getDefaultSharedPreferences(applicationContext))
}