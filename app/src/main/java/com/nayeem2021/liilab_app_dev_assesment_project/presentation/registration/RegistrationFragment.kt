package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentRegistrationBinding
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

class RegistrationFragment : Fragment() {
    private var _binding : FragmentRegistrationBinding ?= null
    private val binding get() = _binding!!
    private val registrationViewModel : RegistrationViewModel by viewModels()
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
        registrationViewModel
    }

    private fun initViews() {
        binding.signUpButton.setOnClickListener {
            val profileData = with(binding) {
                ProfileData(
                    editTextTextEmailAddress.text.toString(),
                    editTextTextPassword.text.toString(),
                    etName.text.toString(),
                    editTextDateOfBirth.text.toString()
                )
            }
            registrationViewModel.requestRegistration(profileData)
        }
    }
}