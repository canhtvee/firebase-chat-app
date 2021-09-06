package com.canhtv.ee.firebasechatapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.data.repositories.SessionRepository
import com.canhtv.ee.firebasechatapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): ViewModel() {

    private val _session = MutableLiveData<Resource<UserSession>>()
    val session: LiveData<Resource<UserSession>> = _session

    fun applySignInSession(userCredential: UserCredential) {
        viewModelScope.launch {
           sessionRepository.applySignInSession(userCredential).collect {
               _session.value = it
           }
        }
    }

    fun applyRegisterSession(userCredential: UserCredential) {
        viewModelScope.launch {
            sessionRepository.applyRegisterSession(userCredential).collect {
                _session.value = it
            }
        }
    }
}