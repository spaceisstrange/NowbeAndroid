package us.nowbe.nowbe.net

import okhttp3.FormBody
import us.nowbe.nowbe.model.exceptions.RequestNotSuccessfulException
import us.nowbe.nowbe.utils.ApiUtils

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

class NowbeUpdateUserAbout(val token: String, val about: String) : NowbeRequest() {
    /**
     * Attempts to add the user of the provided token and returns whether it was or not possible
     */
    fun updateAbout() {
        super.makeRequest()
    }

    override fun getBody(): FormBody {
        // Build the body with the token of the user and the token of the user to add
        return FormBody.Builder()
                .add(ApiUtils.KEY_TOKEN, token)
                .add(ApiUtils.KEY_ABOUT, about)
                .build()
    }

    override fun getRequestUrl(): String {
        return ApiUtils.USER_UPDATE_ABOUT_URL
    }
}