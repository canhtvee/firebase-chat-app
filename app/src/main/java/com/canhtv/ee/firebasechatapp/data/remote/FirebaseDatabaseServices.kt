package com.canhtv.ee.firebasechatapp.data.remote

import android.util.Log
import com.canhtv.ee.firebasechatapp.data.models.Message
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDatabaseServices @Inject constructor(
    private val firebaseDatabaseReference: DatabaseReference
): BaseFirebaseServices() {

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

    @ExperimentalCoroutinesApi
    fun readMessageFlow(child: String): Flow<Resource<ArrayList<Message>>> = callbackFlow {
        val data = ArrayList<Message>()
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { snapshot1 ->
                    data.clear()
                    val element = snapshot1.getValue<Message>()
                    data.add(element!!)
                    trySendBlocking(Resource.Success(data))
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

    @ExperimentalCoroutinesApi
    fun retrieveMessages(conversationId: String): Flow<Resource<Message>> = callbackFlow {
        val listener = object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val msg = snapshot.getValue(Message::class.java) as Message
                trySend(Resource.Success(msg))
                    .onFailure { close(it) }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message))
                this@callbackFlow.close(error.toException())
            }
        }

        firebaseDatabaseReference.child("conversations").child(conversationId).addChildEventListener(listener)
        awaitClose { firebaseDatabaseReference.child("conversations").child(conversationId).removeEventListener(listener) }

    }

    @ExperimentalCoroutinesApi
    fun retrieveUserFlow() = callbackFlow {
        val data = ArrayList<UserProfile>()
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                snapshot.children.forEach { snapshot1 ->
                    val element = snapshot1.getValue(UserProfile::class.java)
                    Log.d("retrieveUserFLow","isOnline: ${element!!.isOnline}")
//                    element!!.uid = snapshot1.key
                    data.add(element)
                }
                trySend(Resource.Success(data))
                    .onFailure { close(it) }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message))
                this@callbackFlow.close(error.toException())
            }
        }
        firebaseDatabaseReference.child("users").addValueEventListener(listener)
        awaitClose { firebaseDatabaseReference.child("users").removeEventListener(listener) }
    }


}