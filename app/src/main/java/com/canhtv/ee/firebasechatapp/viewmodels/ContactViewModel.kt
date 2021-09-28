package com.canhtv.ee.firebasechatapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.data.repositories.HomeRepository
import com.canhtv.ee.firebasechatapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private var _contacts = MutableLiveData<Resource<ArrayList<UserProfile>>>()
    val contact: LiveData<Resource<ArrayList<UserProfile>>> = _contacts

    fun getContacts() {
        viewModelScope.launch {
            homeRepository.getContacts().collect {
                _contacts.value = it
            }
        }
    }

}