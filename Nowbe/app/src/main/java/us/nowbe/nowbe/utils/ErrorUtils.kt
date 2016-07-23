package us.nowbe.nowbe.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.Toast
import us.nowbe.nowbe.R

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

class ErrorUtils {
    companion object {
        /**
         * Shows a toast with the no-connection error
         */
        fun showNoConnectionToast(context: Context) {
            Toast.makeText(context, context.getString(R.string.general_no_internet), Toast.LENGTH_LONG).show()
        }

        /**
         * Shows a toast indicating that the user could not be added
         */
        fun showUserNotAddedToast(context: Context) {
            Toast.makeText(context, context.getString(R.string.profile_could_not_add_error), Toast.LENGTH_LONG).show()
        }

        /**
         * Shows a toast indicating that the user could not be added
         */
        fun showUserNotRemovedToast(context: Context) {
            Toast.makeText(context, context.getString(R.string.profile_could_not_remove_error), Toast.LENGTH_LONG).show()
        }

        /**
         * Shows a general dialog indicating that something was wrong
         */
        fun showGeneralWhoopsDialog(context: Context) {
            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.general_error_title))
                    .setMessage(context.getString(R.string.general_whoops_message))
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
        }

        /**
         * Shows a dialog indicating that the data provided was wrong
         */
        fun showWrongDataDialog(context: Context) {
            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.general_error_title))
                    .setMessage(context.getString(R.string.login_sign_up_error_login_message))
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
        }

        /**
         * Shows a dialog indicating that we have no connection
         */
        fun showNoConnectionDialog(context: Context) {
            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.general_error_title))
                    .setMessage(context.getString(R.string.general_no_connection_error))
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
        }

        /**
         * Shows a dialog indicating that the mail/user already exists
         */
        fun showUserAlreadyExistsDialog(context: Context) {
            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.general_error_title))
                    .setMessage(context.getString(R.string.login_sign_up_error_sign_up_exist_message))
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
        }

        /**
         * Shows a dialog indicating that the user does not exists
         */
        fun showUserDoesNotExists(context: Context) {
            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.general_error_title))
                    .setMessage(context.getString(R.string.profile_does_not_exists_error))
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
        }
    }
}