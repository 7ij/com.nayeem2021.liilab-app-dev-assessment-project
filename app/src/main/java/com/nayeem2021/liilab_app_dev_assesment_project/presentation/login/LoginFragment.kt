package com.nayeem2021.liilab_app_dev_assesment_project.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nayeem2021.liilab_app_dev_assesment_project.MainComponentActivity
import com.nayeem2021.liilab_app_dev_assesment_project.R
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentLoginBinding
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        val observer = Observer<Boolean> { message ->
            when (message) {
                true -> {
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), MainComponentActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
//                    findNavController().navigate(R.id.action_loginFragment_to_mainComponentActivity)
                }
                else -> {
                    Toast.makeText(requireContext(), "Error message: $message", Toast.LENGTH_SHORT).show()
                }
            }
        }
        loginViewModel.loginData.observe(viewLifecycleOwner, observer)
    }

    private fun initViews() {
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        binding.textView14.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        binding.signInButton.setOnClickListener {
            val userName = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val loginData = LoginData(userName, password)
            loginViewModel.requestLogin(loginData)
        }
    }
}
