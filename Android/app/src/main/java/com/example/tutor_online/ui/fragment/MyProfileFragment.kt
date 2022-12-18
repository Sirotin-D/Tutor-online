package com.example.tutor_online.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.databinding.FragmentMyProfileBinding
import com.example.tutor_online.utils.datastorage.MessageConverter
import com.example.tutor_online.ui.activity.AuthActivity
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
        binding.logOutButton.setOnClickListener {
            logOut()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = DataRepository(context).getUserData()
        binding.userNameTextView.text = "Имя: ${user.name}"
        binding.userAgeTextView.text = "Возраст: ${user.age}"
        binding.userPhoneTextView.text = "Телефон: ${user.phone}"
        binding.userTypeTextView.text = "Статус: ${MessageConverter.getStringForUserStatus(user.type)}"
        binding.userMailTextView.text = "Почта: ${user.email}"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorMessage: String) {
        
    }

    private fun logOut() {
        DataRepository(context).deleteUserData()
        val authIntent = Intent(context, AuthActivity::class.java)
        startActivity(authIntent)
    }
}