package com.example.tutor_online.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.databinding.RegisterFragmentBinding
import com.example.tutor_online.datamodel.viewDataModel.RegisterViewDataModel
import com.example.tutor_online.ui.activity.MainActivity
import com.example.tutor_online.utils.datastorage.DataRepository
import com.example.tutor_online.viewmodel.RegisterViewModel

class RegisterFragment: Fragment(), IBaseView {

    private var _binding: RegisterFragmentBinding? = null
    private val binding: RegisterFragmentBinding get() = _binding!!
    private lateinit var viewModel: RegisterViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        binding.acceptCreateButtonLesson.setOnClickListener {
            viewModel.registerButtonPressed(
                binding.registerNameEditText.text.toString(),
                binding.registerAgeEditText.text.toString(),
                binding.registerPhoneEditText.text.toString(),
                binding.registerEmailEditText.text.toString(),
                binding.registerPasswordEditText.text.toString(),
                binding.registerIsTutorSwitch.isActivated
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            registerStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    RegisterViewDataModel.INITIAL_STATE -> {

                    }
                    RegisterViewDataModel.END_REGISTER -> {
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

    }

    override fun hideLoading() {

    }

    override fun showError(errorId: Int?) {

    }
}