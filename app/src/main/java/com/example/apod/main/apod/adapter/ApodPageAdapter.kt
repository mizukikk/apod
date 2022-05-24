package com.example.apod.main.apod.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.apod.R
import com.example.apod.di.ApodApplication
import com.example.apod.main.apod.ApodListFragment
import com.example.apod.main.apod.data.ApodListArgs

class ApodPageAdapter : FragmentStateAdapter {
    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)
    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    val pageList = listOf(
        Type.All,
        Type.Favorite
    )

    fun getTabTitle(pos: Int) = when (pageList[pos]) {
        Type.All -> ApodApplication.getString(R.string.f_apod_all_pic)
        Type.Favorite -> ApodApplication.getString(R.string.f_apod_favorite_pic)
    }

    override fun getItemCount() = pageList.size

    override fun createFragment(position: Int) =
        ApodListFragment
            .newInstance(ApodListArgs(pageList[position]))

    enum class Type {
        All,
        Favorite
    }
}