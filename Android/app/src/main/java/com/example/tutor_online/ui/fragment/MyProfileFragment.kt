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

class MyProfileFragment: Fragment() {

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
        val repos = DataRepository(context)
        val credentials = repos.getUserData()
        val user = credentials.first
        val userName = user.name
        val userAge = user.age
        val userType = user.userType
        val userMail = credentials.second
        binding.userMailTextView.text = userMail
        binding.userAgeTextView.text = userAge
        binding.userNameTextView.text = userName
        binding.userTypeTextView.text = userType
    }
}