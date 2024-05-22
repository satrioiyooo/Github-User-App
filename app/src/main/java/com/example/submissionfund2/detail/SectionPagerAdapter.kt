@file:Suppress("DEPRECATION")

package com.example.submissionfund2.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submissionfund2.R
import com.example.submissionfund2.detail.fragment.FollowersFragment
import com.example.submissionfund2.detail.fragment.FollowingFragment

class SectionPagerAdapter(private val context : Context, fm : FragmentManager, data: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle: Bundle

    init {
        fragmentBundle = data
    }

    @StringRes
    private val tabTitles = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(tabTitles[position])
    }

}