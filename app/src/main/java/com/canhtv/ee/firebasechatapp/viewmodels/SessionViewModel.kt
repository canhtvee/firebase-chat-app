package com.canhtv.ee.firebasechatapp.viewmodels

import androidx.lifecycle.*
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.data.repositories.SessionManager
import com.canhtv.ee.firebasechatapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val sessionManager: SessionManager
): ViewModel() {

    private val _session = MutableLiveData<Resource<UserSession>>()
    val session: LiveData<Resource<UserSession>> = _session

    fun applySignInSession(userCredential: UserCredential) {
        viewModelScope.launch {
            sessionManager.applySignInSession(userCredential).collect {
               _session.value = it
           }
        }
    }

    fun applyRegisterSession(userCredential: UserCredential, userProfile: UserProfile) {
        viewModelScope.launch {
            sessionManager.applyRegisterSession(userCredential, userProfile).collect {
                _session.value = it
            }
        }
    }
}