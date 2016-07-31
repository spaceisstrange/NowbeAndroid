package us.nowbe.nowbe.activities

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_base_tabs.*
import us.nowbe.nowbe.R
import us.nowbe.nowbe.utils.IntentUtils
import us.nowbe.nowbe.utils.TabUtils

class LandingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Default action for the fab
        setupEditFab()

        // Setup the view pager and the tab view
        val adapter = TabUtils.createPagerAdapter(this, supportFragmentManager)
        vpFragmentList.adapter = adapter
        tlTabs.setupWithViewPager(vpFragmentList)

        // TODO: Update the menu of the activity when navigating through the ViewPager
        vpFragmentList.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                val pageTitle = adapter.getPageTitle(position)

                if (pageTitle == getString(R.string.main_feed_tab)) {
                    setupEditFab()
                } else if (pageTitle == getString(R.string.main_notifications_tab)) {
                    // TODO: Show the clear all fab
                } else if (pageTitle == getString(R.string.main_profile_tab)) {
                    // TODO: Think what the fuck to show in here
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Nothing
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Nothing
            }
        })
    }

    override fun hasTabs(): Boolean {
        return true
    }

    /**
     * Setups the fab to the edit profile action
     */
    fun setupEditFab() {
        // Show the pencil in the fab
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_edit_white))

        // Show the Edit Profile Activity when clicking the fab
        fab.setOnClickListener {
            // Create the intent passing the fab position
            val editProfileIntent = Intent(this@LandingActivity, EditProfileActivity::class.java)
            editProfileIntent.putExtra(IntentUtils.FAB_X_POSITION, fab.right)
            editProfileIntent.putExtra(IntentUtils.FAB_Y_POSITION, fab.bottom)

            startActivity(editProfileIntent)

            // Don't show animations, we'll handle that
            overridePendingTransition(0, 0)
        }
    }
}