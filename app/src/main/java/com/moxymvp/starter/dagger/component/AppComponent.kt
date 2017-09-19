package com.moxymvp.starter.dagger.component

import com.moxymvp.starter.dagger.module.AppModule
import com.moxymvp.starter.dagger.module.StorageModule
import com.moxymvp.starter.presenter.UtilityWrapper
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, StorageModule::class))
interface AppComponent {
    fun inject(utilityWrapper: UtilityWrapper)
}