package com.canhtv.ee.firebasechatapp.data.remote

import android.util.Log
import com.canhtv.ee.firebasechatapp.data.models.Message
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDatabaseService @Inject constructor(
    private val firebaseDatabaseReference: DatabaseReference
) {
    suspend fun writeUser(user: FirebaseUser) {
        val hashMap = HashMap<String, String>()
        with(hashMap) {
            put("email", user.email!!)
        }
        with(firebaseDatabaseReference.child("users").child(user.uid).setValue(hashMap)) {
            await()
            addOnSuccessListener {
                Log.d("FirebaseDatabaseService", "writeUser successfully" )
            }
            addOnFailureListener {
                Log.d("FirebaseDatabaseService", "writeUser failed" )
            }
        }
    }

    suspend fun writeMessage(message: Message) {
        with(firebaseDatabaseReference.child("messages").push().setValue(message)){
            await()
            addOnSuccessListener {
                Log.d("FirebaseDatabaseService", "writeMessage successfully" )
            }
            addOnFailureListener {
                Log.d("FirebaseDatabaseService", "writeMessage failed" )
            }
        }
    }

    suspend fun readMessage(): Flow<ArrayList<Message>> = flow {
        val messages = ArrayList<Message>()
        val task: Task<DataSnapshot> = firebaseDatabaseReference.child("messages").get()

        firebaseDatabaseReference.child("messages").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messages.clear()
                snapshot.children.forEach {
                    val msg = it.getValue(Message::class.java)
                    msg!!.messageId = it.key
                    messages.add(msg)
                    Log.d("FirebaseDatabaseService", "${msg.messageId}: ${msg.text}" )
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("FirebaseDatabaseService", "readMessages failed: ${error.toString()}" )
            }
        })
    }

}