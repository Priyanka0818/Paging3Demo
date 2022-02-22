package com.app.paging3demo.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.paging3demo.R
import com.app.paging3demo.adapter.FeedAdapterWithPagination
import com.app.paging3demo.databinding.ActivityFeedBinding
import com.app.paging3demo.listeners.FeedClickListener
import com.app.paging3demo.model.FeedModel
import com.app.paging3demo.viewmodel.FeedViewModel
import com.app.paging3demo.viewmodel.FeedViewModelFactory
import kotlinx.coroutines.launch

class FeedActivity : AppCompatActivity(), FeedClickListener {


    private var feedAdapter: FeedAdapterWithPagination? = null
    var binding: ActivityFeedBinding? = null
    private var pageList: PagingData<FeedModel.Success_data>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed)
        binding?.showProgress = true
        LinearLayoutManager(this@FeedActivity, LinearLayoutManager.VERTICAL, false)

        val feedViewModelFactory = FeedViewModelFactory(this@FeedActivity)
        val feedViewModel: FeedViewModel =
            ViewModelProvider(this, feedViewModelFactory).get(FeedViewModel::class.java)
        observeViewModel(feedViewModel)
    }

    override fun onResume() {
        super.onResume()
        binding?.shimmerFrameLayout?.startShimmer()
    }

    override fun onPause() {
        binding?.shimmerFrameLayout?.stopShimmer()
        super.onPause()
    }

    private fun observeViewModel(viewModel: FeedViewModel) {
        // Update the list when the data changes
        this.lifecycleScope.launch {
            viewModel.pagedFeedListLiveData.observe(this@FeedActivity) { feedModelSuccessData ->
                feedModelSuccessData.let {
                    pageList = it
                    feedAdapter =
                        FeedAdapterWithPagination(this@FeedActivity)
                    feedAdapter?.submitData(lifecycle, feedModelSuccessData)
                    binding?.feedAdapter = feedAdapter
                    binding?.shimmerFrameLayout?.stopShimmer()
                    binding?.shimmerFrameLayout?.visibility = GONE
                    binding?.rvFeeds?.visibility = VISIBLE
                }
            }
        }
    }

    override fun onClick(position: Int) {
        feedAdapter?.notifyItemRemoved(position)
        feedAdapter?.snapshot()
    }
}