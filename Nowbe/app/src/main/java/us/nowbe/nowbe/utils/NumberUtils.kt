package us.nowbe.nowbe.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

class NumberUtils {
    companion object {
        /**
         * Rounded approximations
         */
        private var roundSubffixes = TreeMap<Int, String>()

        init {
            roundSubffixes.put(1000, "k")
            roundSubffixes.put(1000000, "M")
        }

        /**
         * Rounds a given number to an approximation with a suffix. For example: 1650 to 1k
         */
        fun roundNumber(value: Int): String {
            if (value < 1000) return value.toString()

            val entry: Map.Entry<Int, String> = roundSubffixes.floorEntry(value)
            val dividend = entry.key
            val subffix = entry.value

            return Math.floor((value / dividend).toDouble()).toInt().toString() + subffix
        }

        /**
         * Formats a date to the dd/MM/YYYY format
         */
        fun formatDate(date: String): String {
            val formatterIn = SimpleDateFormat("yyyy-MM-dd")
            val unformattedDate = formatterIn.parse(date)

            return SimpleDateFormat("dd-MM-yyyy").format(unformattedDate).replace("-", "/")
        }

        /**
         * Returns the current UNIX timestamp
         */
        fun getCurrentUnixTimestamp(): Long {
            return System.currentTimeMillis() / 1000
        }
    }
}