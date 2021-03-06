package us.nowbe.nowbe.ui.dialogs

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_edit_general_text.view.*
import us.nowbe.nowbe.R
import us.nowbe.nowbe.model.exceptions.RequestNotSuccessfulException
import us.nowbe.nowbe.net.async.UpdateUserCommentObservable
import us.nowbe.nowbe.utils.ErrorUtils
import us.nowbe.nowbe.utils.Interfaces
import us.nowbe.nowbe.utils.SharedPreferencesUtils
import java.io.IOException

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

class EditCommentDialog : EditWithTextFieldDialog() {
    /**
     * Index of the comment
     */
    var commentIndex: Int = 0

    companion object {
        /**
         * Creates a new instance of the dialog with the onDismiss implementation and the default text
         */
        fun newInstance(onDismiss: Interfaces.OnDialogDismiss, commentIndex: Int): EditWithTextFieldDialog {
            val dialog = EditCommentDialog()
            dialog.onDismiss = onDismiss
            dialog.commentIndex = commentIndex
            return dialog
        }
    }

    override fun getTitle(): String {
        return getString(R.string.profile_edit_comment_number_edit, commentIndex)
    }

    override fun isDataValid(view: View): Boolean {
        return true
    }

    override fun getPositiveAction(view: View, onDismiss: Interfaces.OnDialogDismiss): () -> Unit {
        return {
            // Get the token from the user
            val token = SharedPreferencesUtils.getToken(context)!!

            // Get the about text
            val newComment = view.aedCoupleName.text.toString()

            previousSubscription = UpdateUserCommentObservable.create(token, commentIndex - 1, newComment).subscribe(
                    // On Next
                    {
                        // Show a toast confirming the change
                        Toast.makeText(activity,
                                getString(R.string.profile_edit_comments_slots_updated, commentIndex),
                                Toast.LENGTH_SHORT).show()

                        // Call the onDismiss to indicate we've finished already
                        onDismiss.onDismiss()

                        // Dismiss the dialog
                        dismiss()
                    },
                    // On Error
                    {
                        error ->

                        if (error is RequestNotSuccessfulException) {
                            ErrorUtils.showGeneralWhoopsDialog(activity)
                        } else if (error is IOException) {
                            ErrorUtils.showNoConnectionToast(activity)
                        }
                    }
            )
        }
    }
}