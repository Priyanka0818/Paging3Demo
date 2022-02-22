package com.app.paging3demo.apicall

import com.app.paging3demo.apiclient.APIClient

class APICallHandler {
    companion object {
        val apiCallInterface: APICallInterface? =
            APIClient.getClient()?.create(APICallInterface::class.java)
    }
}