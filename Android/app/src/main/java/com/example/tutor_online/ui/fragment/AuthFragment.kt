package com.example.tutor_online.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.databinding.FragmentAuthBinding
import com.example.tutor_online.ui.activity.MainActivity
import com.example.tutor_online.viewmodel.AuthViewModel
import datamodel.viewdatamodel.AuthViewDataModel

class AuthFragment: Fragment() {

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
            val login = binding.authLoginEditText.text.toString()
            val password = binding.authPasswordEditText.text.toString()
            viewModel.handleClickingOnSignIn(login, password)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            authDisplayLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    AuthViewDataModel.INITIAL_STATE -> {
                        binding.authProgressBar.visibility = View.VISIBLE
                    }
                    AuthViewDataModel.SHOW_LOADING -> {
                        binding.authProgressBar.visibility = View.VISIBLE
                    }
                    AuthViewDataModel.HIDE_LOADING -> {
                        binding.authProgressBar.visibility = View.VISIBLE
                    }
                    AuthViewDataModel.SHOW_SIGN_IN -> {
                        binding.authProgressBar.visibility = View.GONE
                        binding.authErrorTextView.visibility = View.GONE
                    }
                    AuthViewDataModel.SHOW_ERROR -> {
                        binding.authProgressBar.visibility = View.GONE
                        binding.authErrorTextView.text = "Ошибка"
                    }
                    AuthViewDataModel.OPEN_MAIN_MENU -> {
                        val mainMenuIntent = Intent(context, MainActivity::class.java)
                        startActivity(mainMenuIntent)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}