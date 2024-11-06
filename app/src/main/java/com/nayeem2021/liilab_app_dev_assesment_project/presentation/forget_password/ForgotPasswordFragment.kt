package com.nayeem2021.liilab_app_dev_assesment_project.presentation.forget_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nayeem2021.liilab_app_dev_assesment_project.R


class ForgotPasswordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }
}