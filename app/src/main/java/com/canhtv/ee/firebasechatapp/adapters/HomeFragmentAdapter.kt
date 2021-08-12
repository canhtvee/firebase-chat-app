package com.canhtv.ee.firebasechatapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.canhtv.ee.firebasechatapp.ui.home.ContactFragment
import com.canhtv.ee.firebasechatapp.ui.home.ConversationFragment

class HomeFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ConversationFragment()
            else -> ContactFragment()
        }
    }
}