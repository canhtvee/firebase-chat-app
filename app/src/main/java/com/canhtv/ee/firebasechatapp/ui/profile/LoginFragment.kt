package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.databinding.FragmentLoginBinding
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.SessionController
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var sessionViewModel: SessionViewModel

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = binding.loginEmailEditText
        val password = binding.loginPasswordEditText

        binding.loginRegisterText.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                .navigate(R.id.action_global_registerFragment)
        }

        sessionViewModel.session.observe(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Success -> {
                    Toast.makeText(context, resources.data.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()

                }
                is Resource.Error -> {
                    Toast.makeText(context, resources.message, Toast.LENGTH_LONG).show()

                }
            }
        }


        binding.loginButton.setOnClickListener {

            if (TextUtils.isEmpty(email.text) or (TextUtils.isEmpty(password.text))) {
                Toast.makeText(context, "Check Credential", Toast.LENGTH_SHORT).show()
            } else {
                val user = UserCredential(email.text.toString().trim(), password.text.toString().trim())
                sessionViewModel.applySignInSession(user)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}