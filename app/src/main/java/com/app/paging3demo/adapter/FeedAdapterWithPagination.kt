package com.app.paging3demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.paging3demo.BR
import com.app.paging3demo.R
import com.app.paging3demo.listeners.FeedClickListener
import com.app.paging3demo.model.FeedModel


class FeedAdapterWithPagination(private var listener: FeedClickListener) :
    PagingDataAdapter<FeedModel.Success_data, FeedAdapterWithPagination.FeedViewHolder>(
        DIFF_CALLBACK
    ) {

    class FeedViewHolder(private var viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(
            itemViewModel: FeedModel.Success_data,
            listener: FeedClickListener,
            position: Int
        ) {
            viewDataBinding.setVariable(BR.feedData, itemViewModel)
            viewDataBinding.setVariable(BR.feedClickListener, listener)
            viewDataBinding.setVariable(BR.position, position)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_feeds,
            parent,
            false
        )

        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position)!!, listener, position)

    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FeedModel.Success_data> =
            object : DiffUtil.ItemCallback<FeedModel.Success_data>() {
                override fun areItemsTheSame(
                    oldItem: FeedModel.Success_data,
                    newItem: FeedModel.Success_data
                ): Boolean {
                    return oldItem.id === newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: FeedModel.Success_data,
                    newItem: FeedModel.Success_data
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}