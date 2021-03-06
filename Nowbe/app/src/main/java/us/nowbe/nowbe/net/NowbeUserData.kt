package us.nowbe.nowbe.net

import okhttp3.FormBody
import us.nowbe.nowbe.model.User
import us.nowbe.nowbe.model.exceptions.UserDoesNotExistsException
import us.nowbe.nowbe.utils.ApiUtils

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

class NowbeUserData(val userToken: String, val profileToken: String) : NowbeRequest() {

    /**
     * Attempts to get an user by its token
     */
    fun getUser(): User {
        // Make the request and get the JSON data returned
        val response = super.makeRequest()
        val json = super.getFirstObjectFromArray(response)

        if (json.length() <= 0) throw UserDoesNotExistsException()

        // Return the user if the request was successful or null otherwise
        return User.fromJson(profileToken, json)
    }

    override fun getBody(): FormBody {
        // Make the body with the token
        return FormBody.Builder()
                .add(ApiUtils.KEY_TOKEN, profileToken)
                .add(ApiUtils.KEY_TOKEN_WANT_TO_SEE, userToken)
                .build()
    }

    override fun getRequestUrl(): String {
        return ApiUtils.USER_DATA_URL
    }
}