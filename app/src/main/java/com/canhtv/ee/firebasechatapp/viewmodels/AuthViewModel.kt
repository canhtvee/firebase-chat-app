package com.canhtv.ee.firebasechatapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    lateinit var AuthState: LiveData<Resource<FirebaseUser>>
}