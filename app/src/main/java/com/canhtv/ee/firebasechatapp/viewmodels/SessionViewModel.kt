package com.canhtv.ee.firebasechatapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesServices
import com.canhtv.ee.firebasechatapp.data.models.SessionData
import com.canhtv.ee.firebasechatapp.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val sharePreferencesServices: SharePreferencesServices
): ViewModel() {

    val _session = MutableLiveData<SessionData>()
    val sessionData: LiveData<SessionData> = _session

    init {
        viewModelScope.launch {
            _session.value = sharePreferencesServices.getSession()
        }
    }
}