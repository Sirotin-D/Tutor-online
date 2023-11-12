package com.example.tutor_online.ui.fragment

interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(errorMessage: String)
}