package com.app.paging3demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.paging3demo.view.FeedActivity

class FeedViewModelFactory(var feedActivity: FeedActivity) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java))
            return FeedViewModel(feedActivity) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}