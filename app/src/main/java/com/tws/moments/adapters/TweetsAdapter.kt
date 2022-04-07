package com.tws.moments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tws.moments.databinding.LayoutBaseTweetBinding
import com.tws.moments.items.TweetItem
import com.tws.moments.viewholders.TweetViewHolder

class TweetsAdapter :
    ListAdapter<TweetItem, TweetViewHolder>(TweetItem.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        return TweetViewHolder(
            LayoutBaseTweetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
