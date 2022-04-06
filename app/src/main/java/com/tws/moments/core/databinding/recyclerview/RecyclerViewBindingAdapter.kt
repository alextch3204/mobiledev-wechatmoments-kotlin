package com.tws.moments.core.databinding.recyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView] binding adapters.
 *
 */

/**
 * Assigns [SingleDataBoundListAdapter] to [RecyclerView] from XML.
 *
 * ```
 * <androidx.recyclerview.widget.RecyclerView
 *     ...
 *     app:items="@{viewModel.accounts}"
 *     app:itemLayout="@{@layout/account_item}"
 *     app:itemDiff="@{viewModel.accountDiff}" />
 * ```
 *
 */
@BindingAdapter("items", "itemLayout", "itemDiff")
fun <T> setAdapter(
    view: RecyclerView,
    items: LiveData<List<T>>,
    @LayoutRes itemLayout: Int,
    itemDiff: DiffUtil.ItemCallback<T>
) {
    if (view.adapter == null) {
        view.adapter = SingleDataBoundListAdapter(items, itemLayout, itemDiff)
    }
}
