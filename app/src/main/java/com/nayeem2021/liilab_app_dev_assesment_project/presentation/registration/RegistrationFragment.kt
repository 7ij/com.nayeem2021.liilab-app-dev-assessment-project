package com.nayeem2021.liilab_app_dev_assesment_project.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nayeem2021.liilab_app_dev_assesment_project.R
import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.AuthRepositoryImpl
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentRegistrationBinding
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.model.ProfileData

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    
    private  val localDataSource = UserLocalDataSource()
    private val registrationRepository : AuthRepository =  AuthRepositoryImpl(localDataSource)
    private val registrationUseCase =  RegistrationUseCase(registrationRepository)
    private val registrationViewModel : RegistrationViewModel by viewModels {RegistrationViewModelFactory(registrationUseCase)}

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
        val observer = Observer<String> {
            Toast.makeText(requireContext(), "Message: $it", Toast.LENGTH_SHORT).show()
        }
        registrationViewModel.registrationUiStatus.observe(viewLifecycleOwner, observer)
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
        binding.tvSignIn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}