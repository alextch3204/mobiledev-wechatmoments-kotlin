package com.tws.moments.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tws.moments.api.MomentRepository
import com.tws.moments.api.entry.UserBean
import com.tws.moments.items.TweetItem
import kotlinx.coroutines.launch
import kotlin.math.min

private const val TAG = "MainViewModel##"

private const val PAGE_TWEET_COUNT = 10

class MainViewModel(private val repository: MomentRepository) : ViewModel() {

    val userBean: MutableLiveData<UserBean> by lazy {
        MutableLiveData<UserBean>().also { loadUserInfo() }
    }

    private val _tweets = MutableLiveData<List<TweetItem>>(emptyList())
    val tweets: LiveData<List<TweetItem>> = _tweets

    private var allTweets: List<TweetItem> = emptyList()
    private var reqPageIndex = 1

    init {
        loadTweets()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            val result = try {
                repository.fetchUser()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            userBean.value = result
        }
    }

    private fun loadTweets() {
        viewModelScope.launch {
            val result: List<TweetItem> = try {
                repository.fetchTweets().mapIndexed { index, tweet -> TweetItem(index, tweet) }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }

            allTweets = result
            reqPageIndex = 1

            if (allTweets.size > PAGE_TWEET_COUNT) {
                _tweets.value = allTweets.subList(0, PAGE_TWEET_COUNT)
            } else {
                _tweets.value = allTweets
            }
        }
    }

    fun refreshTweets() {
        loadTweets()
    }

    val pageCount: Int
        get() {
            return when {
                allTweets.isEmpty() -> 0
                allTweets.size % PAGE_TWEET_COUNT == 0 -> allTweets.size / PAGE_TWEET_COUNT
                else -> allTweets.size / PAGE_TWEET_COUNT + 1
            }
        }

    fun onLoadMore() {
        Log.i(TAG, "load more reqPageIndex:$reqPageIndex,pageCount:${pageCount}")
        if (reqPageIndex <= pageCount - 1) {
            Log.i(TAG, "internal load more")
            loadMoreTweets(reqPageIndex) {
                reqPageIndex++
                _tweets.value = _tweets.value!! + it
            }
        }
    }

    private fun loadMoreTweets(pageIndex: Int, onLoad: (List<TweetItem>) -> Unit) {
        if (pageIndex < 0) {
            throw IllegalArgumentException("page index must greater than or equal to 0.")
        }

        if (pageIndex > pageCount - 1) {
            return
        }

        viewModelScope.launch {
            val startIndex = PAGE_TWEET_COUNT * pageIndex
            val endIndex = min(allTweets.size, PAGE_TWEET_COUNT * (pageIndex + 1))
            val result = allTweets.subList(startIndex, endIndex)
            onLoad(result)
        }
    }

}
