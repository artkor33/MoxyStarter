package com.moxymvp.starter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moxymvp.starter.adapter.base.ExtendedRealmRecyclerViewAdapter
import io.realm.OrderedRealmCollection
import io.realm.RealmObject


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BaseRealmRecyclerViewAdapter<T : RealmObject, VH : RecyclerView.ViewHolder>(data: OrderedRealmCollection<T>, autoUpdate: Boolean = true, updateOnModification: Boolean = true)
    : ExtendedRealmRecyclerViewAdapter<T, VH>(data, autoUpdate, updateOnModification) {

    fun buildView(parent: ViewGroup?, viewType: Int): View = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
}