package com.moxymvp.starter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BaseRecyclerViewAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    fun buildView(parent: ViewGroup?, viewType: Int): View = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
}