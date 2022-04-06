package com.tws.moments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.api.entry.TweetBean
import com.tws.moments.databinding.LayoutBaseTweetBinding
import com.tws.moments.viewholders.TweetViewHolder

class TweetsAdapter :
    RecyclerView.Adapter<TweetViewHolder>() {

    var tweets: MutableList<TweetBean>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        return TweetViewHolder(
            LayoutBaseTweetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tweets?.size ?: 0
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(tweets!![position])
    }

    fun addMoreTweet(tweets: List<TweetBean>?) {
        if (tweets.isNullOrEmpty() || this.tweets.isNullOrEmpty()) {
            return
        }
        val newTweetSize = tweets.size
        val oldTweetsSize = this.tweets!!.size
        this.tweets!! += tweets
        notifyItemRangeInserted(oldTweetsSize, newTweetSize)
    }
}
