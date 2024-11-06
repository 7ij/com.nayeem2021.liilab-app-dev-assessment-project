package com.nayeem2021.liilab_app_dev_assesment_project.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nayeem2021.liilab_app_dev_assesment_project.R
import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.AuthRepositoryImpl
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentLoginBinding
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.LoginUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.LoginData

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val localDataSource = UserLocalDataSource()
    private val authRepository = AuthRepositoryImpl(localDataSource)
    private val loginUseCase = LoginUseCase(authRepository)
    private val loginViewModel: LoginViewModel by viewModels{ LoginViewModelFactory(loginUseCase) }

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
        val observer = Observer<String> { message ->
            when (message) {
                "SUCCESS" -> {
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
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
        binding.signInBtn.setOnClickListener {
            val userName = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val loginData = LoginData(userName, password)
            loginViewModel.requestLogin(loginData)
        }
    }
}
