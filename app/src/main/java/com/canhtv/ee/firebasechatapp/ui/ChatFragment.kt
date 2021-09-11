package com.canhtv.ee.firebasechatapp.ui

import android.net.wifi.hotspot2.pps.Credential
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
//        val database = Firebase.database
//        val msRef = database.getReference("users")
//
//        msRef.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val value = snapshot.value
//                Log.d("TAG", "Value is: $value")
//                msRef.setValue(value)
//            }
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("TAG", "Failed to read value.", error.toException())
//            }
//        })
//
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        val hashMap = HashMap<String, String>()
//        with(hashMap) {
//            put(currentUser!!.uid, currentUser.email!!)
//        }
//        msRef.setValue(hashMap)
//        msRef.child(currentUser!!.uid).setValue(currentUser)
//        msRef.child(currentUser!!.uid).setValue(currentUser.email)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}