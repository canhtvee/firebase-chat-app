package com.canhtv.ee.firebasechatapp.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.adapters.ConversationRecyclerViewAdapter
import com.canhtv.ee.firebasechatapp.data.models.User

class ConversationFragment : Fragment(R.layout.fragment_conversation) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val users = mutableListOf<User>(User("Name 1", "Image 1", "Message 1"))
        for (i in 2..10) {
            val user = User("Name $i", "Image $i", "Message $i")
            users.add(user)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.conversation_recycler_view)
        val itemDivider = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        itemDivider.setDrawable(AppCompatResources.getDrawable(recyclerView.context, R.drawable.item_divider)!!)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                .apply { initialPrefetchItemCount = 6 }
            adapter = ConversationRecyclerViewAdapter(users){
                onItemClick()
            }
            hasFixedSize()
            addItemDecoration(itemDivider)
        }
    }

    private fun onItemClick() {
        val mainNavController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment_container)
        mainNavController.navigate(R.id.action_global_chatFragment)
    }

}