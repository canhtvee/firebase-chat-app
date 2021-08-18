package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.SessionController
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var sessionViewModel: SessionViewModel

    @Inject
    lateinit var sessionController: SessionController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = view.findViewById<TextInputEditText>(R.id.login_email_edit_text)
        val password = view.findViewById<TextInputEditText>(R.id.login_password_edit_text)
        val btn = view.findViewById<MaterialButton>(R.id.login_button)
        val register = view.findViewById<TextView>(R.id.login_register_text)

        val mainNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)

        val auth = FirebaseAuth.getInstance()


        btn.setOnClickListener {
            if (TextUtils.isEmpty(email.text) or (TextUtils.isEmpty(password.text))) {
                Toast.makeText(context, "Check Credential", Toast.LENGTH_SHORT).show()
            } else {
//                sessionViewModel.applySignInSession(UserCredential(email.text.toString(), password.text.toString()))

                val _email = email.text.toString().trim()
                val _password = password.text.toString().trim()
                auth.signInWithEmailAndPassword(_email, _password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val email = user?.email.toString()
                            val id = user?.uid.toString()
                            val other = user?.isEmailVerified.toString()
                            Toast.makeText(context, email + id + other, Toast.LENGTH_LONG).show()
                        }
                    }

            }
        }

//        sessionViewModel.session.observe(viewLifecycleOwner, { resources ->
//            when (resources) {
//                is Resource.Success -> {
//                    sessionController.checkSession(resources.data, mainNavController)
//                }
//                else -> {
//                }
//            }
//        })

        register.setOnClickListener {
            mainNavController.navigate(R.id.action_global_registerFragment)
        }
    }
}