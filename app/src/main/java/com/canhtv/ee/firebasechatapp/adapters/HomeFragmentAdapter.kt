package com.canhtv.ee.firebasechatapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.canhtv.ee.firebasechatapp.ui.home.ContactFragment
import com.canhtv.ee.firebasechatapp.ui.home.ConversationFragment
import com.canhtv.ee.firebasechatapp.ui.home.ProfileFragment

class HomeFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ConversationFragment()
            1 -> ContactFragment()
            else -> ProfileFragment()
        }
    }
}