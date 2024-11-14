package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentRegistrationBinding
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationFormValidityStatus
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val registrationViewModel: RegistrationViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registrationViewModel.uiEffects.collect { effect ->
                    when (effect) {
                        is RegistrationUiEffects.Loading -> {
                            Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is RegistrationUiEffects.RegistrationSuccessful -> {
                            Toast.makeText(
                                requireContext(),
                                "Registration Successful. Please Login Now",
                                Toast.LENGTH_SHORT
                            )
                            findNavController().navigateUp()
                        }

                        is RegistrationUiEffects.FormError -> {
                            handleError(effect.formError)
                        }

                        is RegistrationUiEffects.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Something went wrong. Message: ${effect.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is RegistrationUiEffects.EmailError -> handleEmailError(effect.msg)
                        is RegistrationUiEffects.EmailOk -> handleEmailOk()

                        is RegistrationUiEffects.PasswordError -> handlePasswordError(effect.msg)
                        is RegistrationUiEffects.PasswordOk -> handlePasswordOk()

                        is RegistrationUiEffects.NameError -> handleNameError(effect.msg)
                        is RegistrationUiEffects.NameOk -> handleNameOk()

                        is RegistrationUiEffects.DobError -> handleDobError(effect.msg)
                        is RegistrationUiEffects.DobOk -> handleDobOk()

                    }
                }
            }
        }
    }

    private fun handlePasswordOk() {
        binding.inputLayoutPassword.error = null
    }

    private fun handlePasswordError(errorMsg: String) {
        binding.inputLayoutPassword.error = errorMsg
    }

    private fun handleEmailError(msg: String) {
        binding.inputLayoutEmail.error = msg
    }

    private fun handleEmailOk() {
        binding.inputLayoutEmail.error = null
    }

    private fun handleNameError(msg: String) {
        binding.inputLayoutName.error = msg
    }

    private fun handleNameOk() {
        binding.inputLayoutName.error = null
    }

    private fun handleDobError(msg: String) {
        binding.inputLayoutDateOfBirth.error = msg
    }

    private fun handleDobOk() {
        binding.inputLayoutDateOfBirth.error = null
    }


    private fun handleError(formError: RegistrationFormValidityStatus) {

    }

    private fun initViews() {
        with(binding) {
            signUpButton.setOnClickListener {
                val profileData = with(binding) {
                    ProfileData(
                        editTextEmail.text.toString(),
                        editTextPassword.text.toString(),
                        editTextName.text.toString(),
                        editTextDateOfBirth.text.toString()
                    )
                }
                registrationViewModel.handleEvent(
                    RegistrationUiEvents.OnSubmitButtonTap(profileData)
                )
            }

            tvSignIn.setOnClickListener {
                // go back to sign in fragment
                findNavController().navigateUp()
            }

            editTextEmail.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val email = binding.editTextEmail.text.toString()
                    registrationViewModel.handleEvent(
                        RegistrationUiEvents.OnFinishedWritingEmail(email)
                    )
                }
            }

            editTextPassword.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val password = binding.editTextPassword.text.toString()
                    registrationViewModel.handleEvent(
                        RegistrationUiEvents.onFinishedWritingPassword(
                            password
                        )
                    )
                }
            }

            editTextName.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val name = binding.editTextName.text.toString()
                    registrationViewModel.handleEvent(
                        RegistrationUiEvents.onFinishedWritingName(name)
                    )
                }
            }
            editTextDateOfBirth.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val dateOfBirth = binding.editTextDateOfBirth.text.toString()
                    registrationViewModel.handleEvent(
                        RegistrationUiEvents.onFinishedWritingDob(
                            dateOfBirth
                        )
                    )
                }
            }
        }

    }
}