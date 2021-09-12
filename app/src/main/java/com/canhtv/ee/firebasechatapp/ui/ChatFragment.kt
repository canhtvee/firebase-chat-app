package com.canhtv.ee.firebasechatapp.ui

import android.net.wifi.hotspot2.pps.Credential
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.canhtv.ee.firebasechatapp.data.models.Message
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseDatabaseService
import com.canhtv.ee.firebasechatapp.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    @Inject lateinit var firebaseDatabaseService: FirebaseDatabaseService

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

        val currentUser = FirebaseAuth.getInstance().currentUser
        lifecycleScope.launch {
            firebaseDatabaseService.writeUser(currentUser!!)
        }

        binding.chatSendButton.setOnClickListener {
            lifecycleScope.launch {
//                val msg = binding.chatEditText.text
//                if (!TextUtils.isEmpty(msg)) {
//                    firebaseDatabaseService.writeMessage(Message(currentUser!!.uid, msg.toString().trim()))
//                }

                firebaseDatabaseService.readMessage()

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}