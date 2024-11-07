package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.nayeem2021.liilab_app_dev_assesment_project.R
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentRegistrationBinding
import com.nayeem2021.liilab_app_dev_assesment_project.domain.model.RegistrationStatus
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
                            Toast.makeText(
                                requireContext(),
                                "Input Data is invalid",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is RegistrationUiEffects.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Something went wrong. Message: ${effect.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
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
                findNavController().navigateUp()
            }
        }
    }
}