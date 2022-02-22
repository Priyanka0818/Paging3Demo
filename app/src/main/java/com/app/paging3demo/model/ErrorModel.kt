package com.app.paging3demo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorModel {
    @Expose
    @SerializedName("error_data")
    var error_data: List<String>? = null

    @Expose
    @SerializedName("status")
    var status: String? = null
}