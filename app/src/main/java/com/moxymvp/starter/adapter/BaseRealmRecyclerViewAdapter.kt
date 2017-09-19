package com.moxymvp.starter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.OrderedRealmCollection
import io.realm.RealmObject
import io.realm.RealmRecyclerViewAdapter


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BaseRealmRecyclerViewAdapter<T : RealmObject, VH : RecyclerView.ViewHolder>(data: OrderedRealmCollection<T>, autoUpdate: Boolean = true, updateOnModification: Boolean = true)
    : RealmRecyclerViewAdapter<T, VH>(data, autoUpdate, updateOnModification) {

    fun buildView(parent: ViewGroup?, viewType: Int): View = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
}