package us.nowbe.nowbe.utils

/**
 * This file is part of Nowbe for Android
 *
 * Copyright (c) 2016 The Nowbe Team
 * Maintained by Fran González <@spaceisstrange>
 */

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import us.nowbe.nowbe.R
import us.nowbe.nowbe.adapters.ViewPagerAdapter
import us.nowbe.nowbe.ui.fragments.*

class TabUtils {
    companion object {
        /**
         * Method that will setup the view pager of the landing activity with the tabs
         */
        fun createLandingPagerAdapter(context: Context, fragmentManager: FragmentManager, onUserResult: Interfaces.OnUserResult? = null): ViewPagerAdapter {
            val adapter = object : ViewPagerAdapter(fragmentManager) {
                override fun getItem(position: Int): Fragment {
                    if (fragmentList[position] == null) {
                        if (position == 0) {
                            fragmentList[position] = FeedFragment()
                            fragmentTitleList[position] = context.getString(R.string.main_feed_tab)
                        } else if (position == 1) {
                            fragmentList[position] = ActivityFragment()
                            fragmentTitleList[position] = context.getString(R.string.main_notifications_tab)
                        } else if (position == 2) {
                            fragmentList[position] = ProfileFragment.newInstance(SharedPreferencesUtils.getToken(context)!!, onUserResult)
                            fragmentTitleList[position] = context.getString(R.string.main_profile_tab)
                        }
                    }

                    return fragmentList[position]!!
                }
            }

            adapter.addFragment(FeedFragment(), context.getString(R.string.main_feed_tab))
            adapter.addFragment(ActivityFragment(), context.getString(R.string.main_notifications_tab))

            // Load the profile fragment with the token of the user
            adapter.addFragment(ProfileFragment.newInstance(SharedPreferencesUtils.getToken(context)!!, onUserResult),
                    context.getString(R.string.main_profile_tab))

            return adapter
        }

        /**
         * Method that will setup the view pager of the search activity with the tabs
         */
        fun createSearchPagerAdapter(context: Context, fragmentManager: FragmentManager, onClick: Interfaces.OnSearchResultClick): ViewPagerAdapter {
            val adapter = object : ViewPagerAdapter(fragmentManager) {
                override fun getItem(position: Int): Fragment {
                    if (fragmentList[position] == null) {
                        fragmentList[position] = SearchTypeFragment.newInstance(onClick)

                        var title = ""
                        when (position) {
                            0 -> title = context.getString(R.string.search_title_users)
                            1 -> title = context.getString(R.string.search_title_terms)
                            2 -> title = context.getString(R.string.search_title_interests)
                        }

                        fragmentTitleList[position] = title
                    }

                    return fragmentList[position]!!
                }
            }

            adapter.addFragment(SearchTypeFragment.newInstance(onClick), context.getString(R.string.search_title_users))
            adapter.addFragment(SearchTypeFragment.newInstance(onClick), context.getString(R.string.search_title_terms))
            adapter.addFragment(SearchTypeFragment.newInstance(onClick), context.getString(R.string.search_title_interests))

            return adapter
        }

        /**
         * Method that will setup the view pager of the tutorial activity
         */
        fun createTutorialPagerAdapter(fragmentManager: FragmentManager): ViewPagerAdapter {
            val adapter = object : ViewPagerAdapter(fragmentManager) {
                override fun getItem(position: Int): Fragment {
                    if (fragmentList[position] == null) {
                        fragmentTitleList[position] = ""

                        when (position) {
                            0 -> fragmentList[position] = TutorialFragment.newInstance(ApiUtils.TUTORIAL_FIRST)
                            1 -> fragmentList[position] = TutorialFragment.newInstance(ApiUtils.TUTORIAL_SECOND)
                            2 -> fragmentList[position] = TutorialFragment.newInstance(ApiUtils.TUTORIAL_THIRD, true)
                        }
                    }

                    return fragmentList[position]!!
                }
            }

            adapter.addFragment(TutorialFragment.newInstance(ApiUtils.TUTORIAL_FIRST), "")
            adapter.addFragment(TutorialFragment.newInstance(ApiUtils.TUTORIAL_SECOND), "")
            adapter.addFragment(TutorialFragment.newInstance(ApiUtils.TUTORIAL_THIRD, true), "")

            return adapter
        }
    }
}
