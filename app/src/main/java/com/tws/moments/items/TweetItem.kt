package com.tws.moments.items

import androidx.recyclerview.widget.DiffUtil
import com.tws.moments.api.entry.TweetBean

class TweetItem(
    private val id: Int,
    private val tweet: TweetBean
) {
    val content: String? get() = tweet.content

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TweetItem>() {
            override fun areItemsTheSame(oldItem: TweetItem, newItem: TweetItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TweetItem, newItem: TweetItem): Boolean = false
        }
    }
}