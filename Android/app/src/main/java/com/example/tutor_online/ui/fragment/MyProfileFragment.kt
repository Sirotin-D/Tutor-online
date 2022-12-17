package com.example.tutor_online.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.databinding.FragmentMyProfileBinding
import com.example.tutor_online.utils.datastorage.DataRepository
import com.example.tutor_online.viewmodel.MyProfileViewModel

class MyProfileFragment: Fragment(), IBaseView {

    private var _binding: FragmentMyProfileBinding? = null
    private val binding: FragmentMyProfileBinding get() = _binding!!
    private lateinit var viewModel: MyProfileViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[MyProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = DataRepository(context).getUserData()
        binding.userNameTextView.text = user.user_name
        binding.userAgeTextView.text = user.user_age
        binding.userPhoneTextView.text = user.user_phone
        binding.userTypeTextView.text = user.user_type
        binding.userMailTextView.text = user.user_email
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorId: Int?) {
        
    }
}