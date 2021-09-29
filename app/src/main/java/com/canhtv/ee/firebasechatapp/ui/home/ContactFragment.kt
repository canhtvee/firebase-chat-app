package com.canhtv.ee.firebasechatapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.adapters.ContactRecyclerViewAdapter
import com.canhtv.ee.firebasechatapp.data.models.UserData
import com.canhtv.ee.firebasechatapp.databinding.FragmentContactBinding
import com.canhtv.ee.firebasechatapp.databinding.FragmentConversationBinding
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.viewmodels.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ContactFragment : Fragment() {

    @Inject
    lateinit var contactViewModel: ContactViewModel

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
        val recyclerView = binding.contactRecyclerView
        val itemDivider = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        itemDivider.setDrawable(AppCompatResources.getDrawable(recyclerView.context, R.drawable.item_divider)!!)

        contactViewModel.contacts.observe(viewLifecycleOwner){ resources ->
            when (resources) {
                is Resource.Success -> {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            .apply { initialPrefetchItemCount = 6 }
                        adapter = ContactRecyclerViewAdapter(resources.data){ mainNavController.navigate(R.id.action_global_chatFragment) }
                        hasFixedSize()
                        addItemDecoration(itemDivider)
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(context, resources.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}