package com.example.tutor_online.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tutor_online.R
import com.example.tutor_online.databinding.FragmentAuthBinding
import com.example.tutor_online.ui.activity.MainActivity
import com.example.tutor_online.viewmodel.AuthViewModel
import com.example.tutor_online.datamodel.viewDataModel.AuthViewDataModel
import com.example.tutor_online.utils.datastorage.DataRepository

class AuthFragment: Fragment(), IBaseView {

    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding get() = _binding!!
    private lateinit var viewModel: AuthViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)

        binding.authProgressBar.visibility = View.GONE
        binding.signInButton.setOnClickListener {
            showLoading()
            val login = binding.authLoginEditText.text.toString()
            val password = binding.authPasswordEditText.text.toString()
            viewModel.handleClickingOnSignIn(login, password)
        }

        binding.registerButton.setOnClickListener {
            registerButtonPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            authDisplayLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    AuthViewDataModel.INITIAL_STATE -> {
                        binding.authProgressBar.visibility = View.GONE
                        binding.authErrorTextView.visibility = View.GONE
                    }
                    AuthViewDataModel.SHOW_LOADING -> {
                        showLoading()
                    }
                    AuthViewDataModel.HIDE_LOADING -> {
                        hideLoading()
                    }
                    AuthViewDataModel.SHOW_ERROR -> {
                        showError(it.resourceId)
                    }
                    AuthViewDataModel.OPEN_MAIN_MENU -> {
                        hideLoading()
                        val mainMenuIntent = Intent(context, MainActivity::class.java)
                        val user = it.user
                        if (user != null) {
                            val repos = DataRepository(context)
                            repos.saveUser(user)
                        }
                        startActivity(mainMenuIntent)
                    }
                    else -> {}
                }
            }
            viewOpened()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showLoading() {
        binding.authProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.authProgressBar.visibility = View.GONE
    }

    override fun showError(errorId: Int?) {
        binding.authProgressBar.visibility = View.GONE
        binding.authErrorTextView.text = "Ошибка"
    }

    private fun registerButtonPressed() {
        findNavController().navigate(R.id.registerFragment)
    }
}