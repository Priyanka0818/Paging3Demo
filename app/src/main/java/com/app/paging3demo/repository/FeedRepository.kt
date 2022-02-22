package com.app.paging3demo.repository

import androidx.multidex.BuildConfig
import com.app.paging3demo.apicall.APICallHandler
import com.app.paging3demo.utils.AppConstants
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Response


class FeedRepository {

    companion object {
        suspend fun callAPI(
            jsonObject: JsonObject,
            apiUrl: String,
            method: String
        ): Response<JsonElement>? {

            if (method == AppConstants.HTTPMethod.HTTP_POST) {
                return APICallHandler.apiCallInterface?.callAPIs(
                    auth = "token",
                    app_version = BuildConfig.VERSION_NAME,
                    data = jsonObject,
                    url = apiUrl
                )!!
            }
            return null
        }
    }
}