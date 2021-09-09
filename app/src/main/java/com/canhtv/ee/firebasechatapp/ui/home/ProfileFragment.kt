package com.canhtv.ee.firebasechatapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.adapters.ProfileRecyclerViewAdapter
import com.canhtv.ee.firebasechatapp.databinding.FragmentProfileBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val actions = listOf("Dark Mode", "Notifications", "Log Out")
        val icons = listOf(R.drawable.ic_dark_mode, R.drawable.ic_notifications, R.drawable.ic_exit_to_app)
        binding.profileRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                .apply { initialPrefetchItemCount = 6 }
            adapter = ProfileRecyclerViewAdapter(actions, icons, context)
            hasFixedSize()
        }
    }

}