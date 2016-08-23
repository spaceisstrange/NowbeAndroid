package us.nowbe.nowbe.ui.activities

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_base_no_tabs.*
import us.nowbe.nowbe.R
import us.nowbe.nowbe.net.async.ChangeStatusObservable
import us.nowbe.nowbe.ui.dialogs.ChangeAvailabilityDialog
import us.nowbe.nowbe.utils.SharedPreferencesUtils

/**
 * Defines a base activity used by any other activity of the app
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup the activity and the toolbar
        if (hasTabs()) {
            setContentView(R.layout.activity_base_tabs)
        } else {
            setContentView(R.layout.activity_base_no_tabs)
        }

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu of the base activity
        menuInflater.inflate(R.menu.menu_base_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val selectedId = item?.itemId

        if (selectedId == android.R.id.home) {
            finish()
            return true
        } else if (selectedId == R.id.toolbarLogOut) {
            // Clear the data from the shared preferences
            SharedPreferencesUtils.clearData(applicationContext)

            // Show the welcome activity
            startActivity(Intent(this, WizardActivity::class.java))
            return true
        } else if (selectedId == R.id.toolbarChangeAvailable) {
            // Get the token of the user
            val userToken = SharedPreferencesUtils.getToken(this)!!

            ChangeAvailabilityDialog.createDialog(this,
                    {
                        // Change the availability to available
                        ChangeStatusObservable.create(userToken, true).subscribe()
                    },
                    {
                        // Change the availability to not available
                        ChangeStatusObservable.create(userToken, false).subscribe()
                    }
            ).show()
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * This method will be called whenever the activity has to inflate the content so it'll select
     * it based on this
     */
    abstract fun hasTabs(): Boolean
}
