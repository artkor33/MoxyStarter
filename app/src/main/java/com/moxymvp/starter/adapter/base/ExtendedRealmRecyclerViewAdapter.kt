package com.moxymvp.starter.adapter.base

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import io.realm.*


/**
 * Created by Artem Korolchuk on 12/30/18.
 * <href a="http://blak-it.com"></href>
 */

abstract class ExtendedRealmRecyclerViewAdapter<T : RealmModel, S : RecyclerView.ViewHolder>(private var data: OrderedRealmCollection<T>,
                                                                                             private val autoUpdate: Boolean = true,
                                                                                             private val updateOnModification: Boolean = true) : RecyclerView.Adapter<S>() {

    private var listener: OrderedRealmCollectionChangeListener<out OrderedRealmCollection<T>>? = null


    init {
        if (autoUpdate) {
            listener = createListener()
        }
    }

    private fun createListener(): OrderedRealmCollectionChangeListener<OrderedRealmCollection<T>> {
        return OrderedRealmCollectionChangeListener { _, changeSet ->
            if (changeSet.state == OrderedCollectionChangeSet.State.INITIAL) {
                notifyDataSetChanged()
                return@OrderedRealmCollectionChangeListener
            }
            val deletions = changeSet.deletionRanges
            for (i in deletions.indices.reversed()) {
                val range = deletions[i]
                onItemRangeRemoved(range.startIndex, range.length)
            }

            val insertions = changeSet.insertionRanges
            for (range in insertions) {
                onItemRangeInserted(range.startIndex, range.length)
            }

            if (!updateOnModification) {
                return@OrderedRealmCollectionChangeListener
            }

            val modifications = changeSet.changeRanges
            for (range in modifications) {
                onItemRangeChanged(range.startIndex, range.length)
            }
        }
    }

    open fun onItemRangeRemoved(startIndex: Int, length: Int) {
        notifyItemRangeRemoved(startIndex, length)
    }

    open fun onItemRangeInserted(startIndex: Int, length: Int) {
        notifyItemRangeInserted(startIndex, length)
    }

    open fun onItemRangeChanged(startIndex: Int, length: Int) {
        notifyItemRangeChanged(startIndex, length)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (autoUpdate && isDataValid()) {

            addListener(data)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        if (autoUpdate && isDataValid()) {

            removeListener(data)
        }
    }

    override fun getItemCount(): Int {
        return if (isDataValid()) data.size else 0
    }

    /**
     * Returns the item in the underlying data associated with the specified position.
     *
     * This method will return `null` if the Realm instance has been closed or the index
     * is outside the range of valid adapter data (which e.g. can happen if [.getItemCount]
     * is modified to account for header or footer views.
     *
     * Also, this method does not take into account any header views. If these are present, modify
     * the `index` parameter accordingly first.
     *
     * @param index index of the item in the original collection backing this adapter.
     * @return the item at the specified position or `null` if the position does not exists or
     * the adapter data are no longer valid.
     */
    @Nullable
    fun getItem(index: Int): T? {
        if (index < 0) {
            throw IllegalArgumentException("Only indexes >= 0 are allowed. Input was: $index")
        }

        // To avoid exception, return null if there are some extra positions that the
        // child adapter is adding in getItemCount (e.g: to display footer view in recycler view)
        if (index >= data.size) return null

        return if (isDataValid()) data[index] else null
    }

    /**
     * Returns data associated with this adapter.
     *
     * @return adapter data.
     */
    @Nullable
    fun getData(): OrderedRealmCollection<T>? {
        return data
    }

    /**
     * Updates the data associated to the Adapter. Useful when the query has been changed.
     * If the query does not change you might consider using the automaticUpdate feature.
     *
     * @param data the new [OrderedRealmCollection] to display.
     */
    fun updateData(@Nullable data: OrderedRealmCollection<T>) {
        if (autoUpdate) {
            if (isDataValid()) {

                removeListener(data)
            }
            if (data != null) {
                addListener(data)
            }
        }

        this.data = data
        notifyDataSetChanged()
    }

    private fun addListener(@NonNull data: OrderedRealmCollection<T>) {
        when (data) {
            is RealmResults<*> -> {
                val results = data as RealmResults<T>

                results.addChangeListener(listener!! as OrderedRealmCollectionChangeListener<RealmResults<T>>)
            }
            is RealmList<*> -> {
                val list = data as RealmList<T>

                list.addChangeListener(listener!! as OrderedRealmCollectionChangeListener<RealmList<T>>)
            }
            else -> throw IllegalArgumentException("RealmCollection not supported: " + data.javaClass)
        }
    }

    private fun removeListener(@NonNull data: OrderedRealmCollection<T>) {
        when (data) {
            is RealmResults<*> -> {
                val results = data as RealmResults<T>

                results.removeChangeListener(listener!! as OrderedRealmCollectionChangeListener<RealmResults<T>>)
            }
            is RealmList<*> -> {
                val list = data as RealmList<T>

                list.removeChangeListener(listener!! as OrderedRealmCollectionChangeListener<RealmList<T>>)
            }
            else -> throw IllegalArgumentException("RealmCollection not supported: " + data.javaClass)
        }
    }

    private fun isDataValid(): Boolean {
        return data != null && data.isValid
    }
}