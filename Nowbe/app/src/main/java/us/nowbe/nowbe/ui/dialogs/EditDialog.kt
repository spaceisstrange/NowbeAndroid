package us.nowbe.nowbe.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import rx.Subscription
import us.nowbe.nowbe.R
import us.nowbe.nowbe.utils.Interfaces

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

abstract class EditDialog() : DialogFragment() {
    /**
     * Previous subscription
     */
    var previousSubscription: Subscription? = null
        set(value) {
            // Unsubscribe the previous subscription before overriding it
            field?.unsubscribe()
            field = value
        }

    /**
     * Callback when the dialog is dismissed
     */
    lateinit var onDismiss: Interfaces.OnDialogDismiss

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // View of the dialog (implemented by the subclass)
        val dialogView = getDialogView()

        val dialog = AlertDialog.Builder(activity)
                .setView(dialogView)
                .setTitle(getTitle())
                .setPositiveButton(getString(R.string.profile_edit_update), null)
                .setNegativeButton(getString(R.string.profile_edit_cancel), null)
                .create()

        // Override the positive's button onClickListener so the dialog doesn't dismiss if the data is invalid
        dialog.setOnShowListener {
            val btnOk = dialog.getButton(AlertDialog.BUTTON_POSITIVE)

            btnOk.setOnClickListener {

                // Dismiss the dialog only if the data provided is valid
                if (isDataValid(dialogView)) {
                    // Perform the action of the positive button
                    getPositiveAction(dialogView, onDismiss)()
                }
            }
        }

        return dialog
    }

    /**
     * Method to be implemented by the subclass and that's going to be called when the dialog needs the view of the dialog
     */
    abstract fun getDialogView(): View

    /**
     * Method to be implemented by the subclass and that's going to be called when the dialog needs a title
     */
    abstract fun getTitle(): String?

    /**
     * Method to be implemented by the subclass and that's going to be called when the dialog calls the positive action
     */
    abstract fun getPositiveAction(view: View, onDismiss: Interfaces.OnDialogDismiss): () -> Unit

    /**
     * Method to call when we need to check whether the data (input of the user) is or not valid
     */
    abstract fun isDataValid(view: View): Boolean

    override fun onDestroy() {
        previousSubscription?.unsubscribe()
        super.onDestroy()
    }
}