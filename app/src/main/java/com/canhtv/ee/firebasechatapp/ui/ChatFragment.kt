package com.canhtv.ee.firebasechatapp.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.canhtv.ee.firebasechatapp.data.models.Message
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseDatabaseServices
import com.canhtv.ee.firebasechatapp.databinding.FragmentChatBinding
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    @Inject lateinit var firebaseDatabaseServices: FirebaseDatabaseServices

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

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val currentUser = FirebaseAuth.getInstance().currentUser

        binding.chatSendButton.setOnClickListener {
            lifecycleScope.launch {
                val text = binding.chatEditText.text
                if (!TextUtils.isEmpty(text)) {
                    val msg = Message(currentUser!!.uid, text.toString().trim())
                    Log.d("msg:", msg.toString())
                    binding.chatEditText.text!!.clear()
                    firebaseDatabaseServices.writeMessage(msg)
                }
            }
        }

        lifecycleScope.launch {
            firebaseDatabaseServices.readMessageFlow ("messages").collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        Toast.makeText(context, "readMessageFlow Successful ${resource.data.toString()}", Toast.LENGTH_LONG).show()
                        resource.data.forEach { Log.d("readMessageFlow:", "${it.senderId}: ${it.text!!}") }
                    }
                    else -> {
                        Toast.makeText(context, "readMessageFlow Failure", Toast.LENGTH_LONG).show()
                        Log.d("readMessageFlow:", "failure")
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}