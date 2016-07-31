package us.nowbe.nowbe.net.async

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import us.nowbe.nowbe.net.NowbeAddUser
import us.nowbe.nowbe.net.NowbeUpdateAboutUser

class UpdateAboutUserObservable {
    companion object {
        /**
         * Returns an observable that adds an user as a friend to the app user's account
         */
        fun create(token: String, about: String): Observable<Unit> {
            return Observable.fromCallable({
                NowbeUpdateAboutUser(token, about).updateAbout()
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}