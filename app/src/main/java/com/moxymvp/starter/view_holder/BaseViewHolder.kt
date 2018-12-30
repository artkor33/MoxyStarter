package com.moxymvp.starter.view_holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Artem Korolchuk on 19.09.17.
 * <href a="http://blak-it.com"></href>
 */

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context: Context = itemView.context
}