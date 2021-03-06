package us.nowbe.nowbe.utils

import us.nowbe.nowbe.model.BottomSheetItem
import us.nowbe.nowbe.model.CommentReply
import us.nowbe.nowbe.model.SearchResult
import us.nowbe.nowbe.model.User
import java.io.Serializable

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

class Interfaces {
    /**
     * Interface to implement when a picture slot is clicked
     */
    interface OnPictureSlotClick {
        /**
         * (Should) return the position of the item clicked so the adapter can show the photo
         */
        fun onPictureSlotClick(itemSelected: Int, adapterPosition: Int)
    }

    interface OnCommentSlotClick {
        /**
         * (Should) return the position of the item clicked so the adapter can do whatever it wants with the comment
         */
        fun onCommentSlotClick(commentText: String, commentIndex: Int)
    }

    /**
     * Interface to implement when a feed item is clicked
     */
    interface OnFeedItemClick {
        /**
         * (Should) return the position of the item clicked so the adapter can react to it
         */
        fun onFeedItemClick(itemSelected: Int)
    }

    /**
     * Interface to implement when the profile fragment (or similar) needs to return an user
     */
    interface OnUserResult {
        /**
         * (Should) return the user that we got from the API call
         */
        fun onUserResult(user: User)
    }

    /**
     * Interface to implement when the user pressed and item on a Bottom Sheet
     */
    interface OnBottomSheetItemClick {
        /**
         * (Should) return the item clicked
         */
        fun onClick(item: BottomSheetItem)
    }

    /**
     * Interface to call when we have a path for a temporary image
     */
    interface OnTemporaryImagePath {
        /**
         * (Should) pass the path of the temporary image
         */
        fun onImagePath(imagePath: String)
    }

    /**
     * Interface to call when a dialog is dismissed
     */
    interface OnDialogDismiss {
        /**
         * (Should) be called when the dialog has finished doing its thing
         */
        fun onDismiss()
    }

    /**
     * Interface to call when a comment feedback is clicked
     */
    interface OnCommentReplyClick {
        /**
         * (Should) pass the information of that item
         */
        fun onClick(content: CommentReply)
    }

    /**
     * Interface to call when a comment feedback is long clicked
     */
    interface OnCommentReplyLongClick {
        /**
         * (Should) pass the information of that item
         */
        fun onLongClick(content: CommentReply)
    }

    /**
     * Interface to call when the user presses the remove button in an activity view
     */
    interface OnActivityDeleteClick {
        /**
         * (Should) pass the id of that item
         */
        fun onActivityDeleteClick(id: String, index: Int)
    }

    /**
     * Interface to call when the user presses a search result
     */
    interface OnSearchResultClick {
        /**
         * (Should) pass the search result
         */
        fun onSearchResultClick(searchResult: SearchResult)
    }

    /**
     * Interface to call when the user presses a profile pic
     */
    interface OnProfilePictureClick {
        /**
         * (Should) pass the picture directory
         */
        fun onProfilePicClick(pictureDir: String)
    }
}