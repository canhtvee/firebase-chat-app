package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthService
import com.canhtv.ee.firebasechatapp.data.repositories.SessionRepository
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.SessionController
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var firebaseAuthService: FirebaseAuthService

//    @Inject
//    lateinit var sessionRepository: SessionRepository
//
//    @Inject
//    lateinit var sessionViewModel: SessionViewModel

    @Inject
    lateinit var sessionController: SessionController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = view.findViewById<TextInputEditText>(R.id.login_email_edit_text)
        val password = view.findViewById<TextInputEditText>(R.id.login_password_edit_text)
        val btn = view.findViewById<MaterialButton>(R.id.login_button)
        val register = view.findViewById<TextView>(R.id.login_register_text)

        val mainNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)



        btn.setOnClickListener {
            val _email = email.text.toString().trim()
            val _password = password.text.toString().trim()
            val _user = UserCredential(_email, _password)

            if (TextUtils.isEmpty(email.text) or (TextUtils.isEmpty(password.text))) {
                Toast.makeText(context, "Check Credential", Toast.LENGTH_SHORT).show()
            } else {
//                sessionViewModel.applySignInSession(UserCredential(_email, _password))
                lifecycleScope.launch {
                    Log.d("TAG Login Session", "start coroutine")
                    when (val result = firebaseAuthService.signInWithEmailAndPassword(_user)) {
                        is Resource.Loading -> {
                            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Success<*> -> {
                            val user = result.data as FirebaseUser
                            Toast.makeText(context, "UserId ${user.uid.toString()}", Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

//                val _email = email.text.toString().trim()
//                val _password = password.text.toString().trim()
//                auth.signInWithEmailAndPassword(_email, _password)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            val user = auth.currentUser
//                            val email = user?.email.toString()
//                            val id = user?.uid.toString()
//                            val other = user?.isEmailVerified.toString()
//                            Toast.makeText(context, email + id + other, Toast.LENGTH_LONG).show()
//                        } else {
//                            Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show()
//                        }
//                    }

            }
        }

//        sessionViewModel.session.observe(viewLifecycleOwner, { resources ->
//            when (resources) {
//                is Resource.Success -> {
//                    Toast.makeText(context, resources.data.credential.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("TAG Login Successful", resources.data.credential.toString())
//                }
//                else -> {
//                    Log.d("TAG Login Failed", resources.toString())
//                }
//            }
//        })

        register.setOnClickListener {
            mainNavController.navigate(R.id.action_global_registerFragment)
        }
    }
}