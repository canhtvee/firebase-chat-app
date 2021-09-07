package com.canhtv.ee.firebasechatapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.adapters.ContactRecyclerViewAdapter
import com.canhtv.ee.firebasechatapp.data.models.UserData
import com.canhtv.ee.firebasechatapp.databinding.FragmentContactBinding
import com.canhtv.ee.firebasechatapp.databinding.FragmentConversationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContactFragment : Fragment() {

    @Inject
    lateinit var mainNavController: NavController

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val users = mutableListOf<UserData>(UserData("Name 1", "Image 1", "Message 1"))
        for (i in 2..10) {
            val user = UserData("Name $i", "Image $i", "Message $i")
            users.add(user)
        }

        val recyclerView = binding.contactRecyclerView
        val itemDivider = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        itemDivider.setDrawable(AppCompatResources.getDrawable(recyclerView.context, R.drawable.item_divider)!!)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                .apply { initialPrefetchItemCount = 6 }
            adapter = ContactRecyclerViewAdapter(users){ mainNavController.navigate(R.id.action_global_chatFragment) }
            hasFixedSize()
            addItemDecoration(itemDivider)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}