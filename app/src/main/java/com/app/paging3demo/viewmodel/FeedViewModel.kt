package com.app.paging3demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.app.paging3demo.datasource.FeedDatasource
import com.app.paging3demo.datasource.PAGE_SIZE
import com.app.paging3demo.view.FeedActivity


class FeedViewModel(feedActivity: FeedActivity) :
    ViewModel() {

    val pagedFeedListLiveData =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { FeedDatasource(feedActivity) }
        ).liveData
}