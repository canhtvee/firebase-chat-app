package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
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
class RegisterFragment : Fragment(R.layout.fragment_register) {

    @Inject
    lateinit var sessionViewModel: SessionViewModel

    @Inject
    lateinit var sessionController: SessionController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layout = view.findViewById<LinearLayoutCompat>(R.id.register_layout)
        val layoutSuccessful = view.findViewById<LinearLayoutCompat>(R.id.register_layout_successful)
        layout.visibility = View.VISIBLE
        layoutSuccessful.visibility = View.GONE

        val email = view.findViewById<TextInputEditText>(R.id.register_email_edit_text)
        val password = view.findViewById<TextInputEditText>(R.id.register_password_edit_text)
        val confirmPassword = view.findViewById<TextInputEditText>(R.id.register_confirm_password_edit_text)
        val btn = view.findViewById<MaterialButton>(R.id.register_button)

        val mainNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)

        btn.setOnClickListener {
            if (TextUtils.isEmpty(email.text) or (TextUtils.isEmpty(password.text)) or (password.text.toString() != confirmPassword.text.toString())) {
                Toast.makeText(context, "Check Credential", Toast.LENGTH_SHORT).show()
            } else {
                //sessionViewModel.applyRegisterSession(UserCredential(email.text.toString(), password.text.toString()))

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnSuccessListener { task ->
                        Log.d("Auth TAG", task.toString())
                    }
            }
        }

        sessionViewModel.session.observe(viewLifecycleOwner, { resources ->
            when (resources) {
                is Resource.Success -> {
                    sessionController.checkSession(resources.data, mainNavController )
                }
                else -> {}
            }
        })
    }
}