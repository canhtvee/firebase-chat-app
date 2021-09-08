package com.canhtv.ee.firebasechatapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.databinding.FragmentChatBinding
import com.canhtv.ee.firebasechatapp.databinding.FragmentConversationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.chatTextInput.isHintAnimationEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}