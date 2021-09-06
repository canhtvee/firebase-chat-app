package com.canhtv.ee.firebasechatapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.databinding.FragmentRegisterBinding
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    @Inject
    lateinit var sessionViewModel: SessionViewModel

    @Inject
    lateinit var mainNavController: NavController

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerLayoutSuccessful.visibility = View.GONE
        binding.registerLayout.visibility = View.VISIBLE

        val email = binding.registerEmailEditText
        val password = binding.registerPasswordEditText
        val confirmPassword = binding.registerConfirmPasswordEditText

        sessionViewModel.session.observe(viewLifecycleOwner) { resources ->
            when (resources) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        binding.registerLayout.visibility = View.GONE
                        binding.registerLayoutSuccessful.visibility = View.VISIBLE
                        delay(500)
                        mainNavController.navigate(R.id.action_global_homeFragment)
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(context, resources.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.registerButton.setOnClickListener {
            if (TextUtils.isEmpty(email.text) or (TextUtils.isEmpty(password.text)) or (password.text.toString() != confirmPassword.text.toString())) {
                Toast.makeText(context, "Check Credential", Toast.LENGTH_SHORT).show()
            }else {
                val user = UserCredential(email.text.toString().trim(), password.text.toString().trim())
                sessionViewModel.applyRegisterSession(user)

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

