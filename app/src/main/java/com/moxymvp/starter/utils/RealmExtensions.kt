package com.moxymvp.starter.utils

import io.realm.RealmChangeListener
import io.realm.RealmObject
import io.realm.RealmResults


/**
 * Created by Artem Korolchuk on 12/30/18.
 * <href a="http://blak-it.com"></href>
 */

fun <T : RealmResults<*>> T.doOnChanged(action: () -> Unit) {
    addChangeListener { _, _ -> action() }
}

fun <T : RealmObject> T.doOnChanged(action: () -> Unit) {
    addChangeListener(RealmChangeListener<T> {
        action()
    })
}