package com.canhtv.ee.firebasechatapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.data.repositories.SessionRepository
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): ViewModel() {

    private val _trial = MutableLiveData<Resource<UserSession>>()
    val trial: LiveData<Resource<UserSession>> = _trial

    fun applySignInSession(userCredential: UserCredential) {
        Log.d("TAG CALL", "SessionViewModel: call applySignInSession")
        viewModelScope.launch {
           sessionRepository.applySignInSession(userCredential).collect {
               _trial.value = it
           }
        }
    }


    fun applyRegisterSession(userCredential: UserCredential) {
        viewModelScope.launch {
            sessionRepository.applyRegisterSession(userCredential).collect {
                _trial.value = it
            }
        }
    }
}