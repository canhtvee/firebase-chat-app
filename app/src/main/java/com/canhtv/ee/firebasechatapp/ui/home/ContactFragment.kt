package com.canhtv.ee.firebasechatapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.adapters.ContactRecyclerViewAdapter
import com.canhtv.ee.firebasechatapp.data.models.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContactFragment : Fragment(R.layout.fragment_contact) {

    @Inject lateinit var mainNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val users = mutableListOf<User>(User("Name 1", "Image 1", "Message 1"))
        for (i in 2..10) {
            val user = User("Name $i", "Image $i", "Message $i")
            users.add(user)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.contact_recycler_view)
        val itemDivider = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        itemDivider.setDrawable(AppCompatResources.getDrawable(recyclerView.context, R.drawable.item_divider)!!)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                .apply { initialPrefetchItemCount = 6 }
            adapter = ContactRecyclerViewAdapter(users){
                onItemClick()
            }
            hasFixedSize()
            addItemDecoration(itemDivider)
        }
    }

    private fun onItemClick() {
//        val mainNavController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment_container)
        mainNavController.navigate(R.id.action_homeFragment_to_navigation3)
    }
}