package com.example.tutor_online.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tutor_online.ui.fragment.IAuthView

class MyProfileViewModel: ViewModel(), IAuthView {

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorId: Int) {

    }
}