package us.nowbe.nowbe.ui.activities

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_base_no_tabs.*
import us.nowbe.nowbe.R
import us.nowbe.nowbe.net.async.UpdateOnlineObservable
import us.nowbe.nowbe.utils.ErrorUtils
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

        supportActionBar?.title = ""

        val nowbeTypeface = Typeface.createFromAsset(assets, "fonts/opensans.ttf")
        logo.typeface = nowbeTypeface
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
        } else if (selectedId == R.id.toolbarSettings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()

        // Set the user as offline
        val userToken = SharedPreferencesUtils.getToken(this)
        if (userToken != null) {
            UpdateOnlineObservable.create(userToken, 0).subscribe(
                    {
                        // Nothing
                    },
                    {
                        error ->

                        ErrorUtils.showNoConnectionDialog(this)
                    }
            )
        }
    }

    /**
     * This method will be called whenever the activity has to inflate the content so it'll select
     * it based on this
     */
    abstract fun hasTabs(): Boolean
}
