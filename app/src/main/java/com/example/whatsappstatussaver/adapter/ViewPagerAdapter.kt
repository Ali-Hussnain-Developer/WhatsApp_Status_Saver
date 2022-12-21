package com.example.whatsappstatussaver.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whatsappstatussaver.constants.Constants
import com.example.whatsappstatussaver.ui.fragments.ImageFragment
import com.example.whatsappstatussaver.ui.fragments.VideoFragment

class ViewPagerAdapter(
    fm: FragmentManager,
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        if (position == 0)
            fragment =
                ImageFragment()
        else if (position == 1)
            fragment =
                VideoFragment()
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0)
            title = Constants.TXT_IMAGES
        else if (position == 1)
            title = Constants.TXT_VIDEOS
        return title
    }
}