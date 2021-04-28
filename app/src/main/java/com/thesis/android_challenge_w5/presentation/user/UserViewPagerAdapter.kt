package com.thesis.android_challenge_w5.presentation.user

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.thesis.android_challenge_w5.presentation.favorite.FavoriteFragment
import com.thesis.android_challenge_w5.presentation.top.TopFragment


class UserViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val fragmentList = listOf<Fragment>(TopFragment(),FavoriteFragment())
    companion object {
        const val TOP_PAGE = 0
        const val FAVORITE_PAGE = 1

        const val MAX_PAGES = 2
    }

    override fun getItem(position: Int): Fragment {
       return fragmentList[position]
    }

    override fun getCount(): Int {
        return MAX_PAGES
    }
}