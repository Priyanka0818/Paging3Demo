package com.app.paging3demo.apicall

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url


interface APICallInterface {
    @POST
    suspend fun callAPIs(
        @Header("Authorization") auth: String,
        @Header("app-version") app_version: String,
        @Body data: JsonObject,
        @Url url: String
    ): Response<JsonElement>
}