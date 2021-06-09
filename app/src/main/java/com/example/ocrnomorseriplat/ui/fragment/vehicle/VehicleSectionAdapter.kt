package com.example.ocrnomorseriplat.ui.fragment.vehicle

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ocrnomorseriplat.R
import com.example.ocrnomorseriplat.ui.fragment.vehicle.list.ListFragment

class VehicleSectionAdapter(fm: FragmentManager, context: Context): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val tabTittles = listOf(
        context.getString(R.string.visitor),
        context.getString(R.string.host)
    )

    override fun getItem(position: Int): Fragment = ListFragment.newInstance(position)

    override fun getPageTitle(position: Int): CharSequence = tabTittles[position]

    override fun getCount(): Int = tabTittles.size

}