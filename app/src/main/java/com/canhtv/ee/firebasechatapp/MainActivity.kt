package com.canhtv.ee.firebasechatapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.utils.SessionController
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var sessionController: SessionController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       sessionController.checkSession()

    }
}