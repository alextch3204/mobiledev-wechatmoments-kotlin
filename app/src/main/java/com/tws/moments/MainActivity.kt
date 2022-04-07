package com.tws.moments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.tws.moments.adapters.HeaderAdapter
import com.tws.moments.adapters.TweetsAdapter
import com.tws.moments.api.MomentRepository
import com.tws.moments.databinding.ActivityMainBinding
import com.tws.moments.utils.ScreenAdaptiveUtil
import com.tws.moments.utils.dip
import com.tws.moments.viewmodels.MainViewModel
import com.tws.moments.viewmodels.MainViewModelFactory
import com.tws.moments.views.LoadMoreListener
import com.tws.moments.views.itemdecoration.MomentDividerItemDecoration

private const val TAG = "MainActivity##"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        val repository = MomentRepository()
        MainViewModelFactory(repository)
    }

    private val headerAdapter = HeaderAdapter()
    private val tweetsAdapter = TweetsAdapter()
    private val adapter = ConcatAdapter(headerAdapter, tweetsAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()

        ScreenAdaptiveUtil.adaptive(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        subscribe()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            MomentDividerItemDecoration(
                offsets = dip(10),
                dividerColor = Color.parseColor("#dddddd"),
                startPosition = 1
            )
        )

        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshTweets()
        }

        binding.recyclerView.addOnScrollListener(object : LoadMoreListener() {
            override fun onLoadMore() {
                viewModel.onLoadMore()
            }
        })
    }

    private fun subscribe() {
        viewModel.userBean.observe(this) {
            headerAdapter.userBean = it
        }

        viewModel.tweets.observe(this) { list ->
            binding.swipeRefreshLayout.isRefreshing = false
            tweetsAdapter.submitList(list)
        }
    }

    private fun initWindow() {
        val flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.decorView.systemUiVisibility = flag
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}
