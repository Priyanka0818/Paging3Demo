package com.app.paging3demo.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.paging3demo.model.FeedModel
import com.app.paging3demo.repository.FeedRepository
import com.app.paging3demo.utils.AppConstants
import com.app.paging3demo.view.FeedActivity
import com.google.gson.Gson
import com.google.gson.JsonObject

const val PAGE_SIZE = 5
class FeedDatasource(private var application: FeedActivity) :
    PagingSource<Int, FeedModel.Success_data>() {

    private var feedModel: FeedModel? = FeedModel()

    private fun getJsonObject(page_key: Int): JsonObject {
        val jsonObject = JsonObject()
        jsonObject.addProperty("keyword", "")
        jsonObject.addProperty("limit", PAGE_SIZE)
        jsonObject.addProperty("page", page_key)
        jsonObject.addProperty("lat", "23.0208241")
        jsonObject.addProperty("lng", "72.5086395")
        return jsonObject
    }

    override fun getRefreshKey(state: PagingState<Int, FeedModel.Success_data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedModel.Success_data> {
        try {
            var nextPage: Int? = params.key ?: 1
            if (nextPage!! <= feedModel?.total_page!!) {
                feedModel = Gson().fromJson(
                    FeedRepository.callAPI(
                        getJsonObject(nextPage),
                        AppConstants.APIMarks.FEED_LIST,
                        AppConstants.HTTPMethod.HTTP_POST
                    )?.body(), FeedModel::class.java
                )
                application.binding?.showProgress = false
            } else {
                nextPage = null
            }
            return LoadResult.Page(
                data = feedModel?.success_data ?: emptyList(),
                prevKey = if (nextPage == 1) null else nextPage!! - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}