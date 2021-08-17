package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    @Inject
    lateinit var sessionViewModel: SessionViewModel

    @Inject
    lateinit var mainNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layout = view.findViewById<LinearLayout>(R.id.register_layout)
        val layoutSuccessful = view.findViewById<LinearLayout>(R.id.register_layout_successful)
        layout.visibility = View.VISIBLE
        layoutSuccessful.visibility = View.GONE

        val email = view.findViewById<TextInputEditText>(R.id.register_email_text_input)
        val password = view.findViewById<TextInputEditText>(R.id.register_password_text_input)
        val confirmPassword = view.findViewById<TextInputEditText>(R.id.register_confirm_password_text_input)
        val btn = view.findViewById<MaterialButton>(R.id.register_button)

        btn.setOnClickListener {
            if (TextUtils.isEmpty(email.text) or (TextUtils.isEmpty(password.text)) or (password.text != confirmPassword.text)) {
                Toast.makeText(context, "Check Credential", Toast.LENGTH_SHORT).show()
            } else {
                sessionViewModel.applyRegisterSession(UserCredential(email.text.toString(), password.text.toString()))
            }
        }

        sessionViewModel.session.observe(viewLifecycleOwner, { resources ->
            when (resources) {
                is Resource.Success -> {
                    layout.visibility = View.GONE
                    layoutSuccessful.visibility = View.VISIBLE
                    mainNavController.navigate(R.id.action_global_homeFragment)
                }
                else -> {}
            }
        })

    }
}