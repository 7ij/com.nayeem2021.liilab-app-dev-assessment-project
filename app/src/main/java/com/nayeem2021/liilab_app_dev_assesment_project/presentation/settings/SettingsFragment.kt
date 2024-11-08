package com.nayeem2021.liilab_app_dev_assesment_project.presentation.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nayeem2021.liilab_app_dev_assesment_project.data.model.ActionResponse
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.databinding.FragmentSettingsBinding
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.session.SessionManagerImpl
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.FakeActionUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.AuthMainActivity.AuthMainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var localDataSource: UserLocalDataSource

    @Inject
    lateinit var fakeAction: FakeActionUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteTokenButton.setOnClickListener {
            val token = SessionManagerImpl.getToken()
            Log.i("SettingsFragment", "Token: $token")
            if (token == null) {
                Toast.makeText(requireContext(), "Token is already null", Toast.LENGTH_SHORT).show()
            } else {
                localDataSource.simulateTokenInvalidationInServer(token)
                Toast.makeText(
                    requireContext(),
                    "Invalidated token in server",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.performActionButton.setOnClickListener {
            val token = SessionManagerImpl.getToken()
            Log.i("debugTag", "liveData: ${SessionManagerImpl.liveData.value}")
            if (token == null) {
                Toast.makeText(
                    requireContext(),
                    "Token is NULL. Something fishy going on",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val result = fakeAction(token!!)
                Log.i("debugTag", "result: $result")
                when(result) {
                    is ActionResponse.Error -> {
                        Toast.makeText(requireContext(), "${result.error}", Toast.LENGTH_SHORT).show()
                    }
                    is ActionResponse.InvalidToken -> {
                        Toast.makeText(requireContext(), "Invalid token. Login again", Toast.LENGTH_SHORT).show()
                        requireActivity().finish()
                        val intent = Intent(requireContext(), AuthMainActivity::class.java)
                        startActivity(intent)
                    }
                    is ActionResponse.Success -> {
                        Toast.makeText(requireContext(), "Action performed successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}