package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = view.findViewById<TextInputEditText>(R.id.login_email_text_input)
        val password = view.findViewById<TextInputEditText>(R.id.login_password_text_input)
        val btn = view.findViewById<MaterialButton>(R.id.login_button)
        val register = view.findViewById<TextView>(R.id.login_register_text)

        btn.setOnClickListener {
            if (!(email.equals(null) or (password.equals(null)))) {

            }
        }
    }
}