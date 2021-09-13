package com.canhtv.ee.firebasechatapp.data.remote

import android.util.Log
import com.canhtv.ee.firebasechatapp.data.models.Message
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    fun readMessage() {
        val messages = ArrayList<Message>()
        firebaseDatabaseReference.child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messages.clear()
                    snapshot.children.forEach {
                        val msg = it.getValue(Message::class.java)
                        msg!!.messageId = it.key
                        messages.add(msg)
                        Log.d("FirebaseDatabaseService", "${msg.messageId}: ${msg.text}")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("FirebaseDatabaseService", "readMessages failed: ${error.toString()}")
                }
            })

    }


    @ExperimentalCoroutinesApi
    fun readMessageFlow(child: String): Flow<Resource<ArrayList<Message>>> = callbackFlow {
        val messages = ArrayList<Message>()
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { snapshot1 ->
                    messages.clear()
                    val msg = snapshot1.getValue(Message::class.java)
                    msg!!.messageId = snapshot1.key
                    messages.add(msg)
                    trySendBlocking(Resource.Success(messages))
                        .onFailure { close(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message))
                this@callbackFlow.close(error.toException())
            }
        }
        firebaseDatabaseReference.child(child).addValueEventListener(listener)
        awaitClose { firebaseDatabaseReference.child(child).removeEventListener(listener) }
    }
}