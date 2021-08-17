package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var sessionViewModel: SessionViewModel

    @Inject
    lateinit var mainNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = view.findViewById<TextInputEditText>(R.id.login_email_text_input)
        val password = view.findViewById<TextInputEditText>(R.id.login_password_text_input)
        val btn = view.findViewById<MaterialButton>(R.id.login_button)
        val register = view.findViewById<TextView>(R.id.login_register_text)

        btn.setOnClickListener {
            if (TextUtils.isEmpty(email.text) or (TextUtils.isEmpty(password.text))) {
                Toast.makeText(context, "Check Credential", Toast.LENGTH_SHORT).show()
            } else {
                sessionViewModel.applySignInSession(UserCredential(email.text.toString(), password.text.toString()))
            }
        }

        register.setOnClickListener {
            mainNavController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}