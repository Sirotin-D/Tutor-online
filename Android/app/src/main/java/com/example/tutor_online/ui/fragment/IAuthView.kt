package com.example.tutor_online.ui.fragment

interface IAuthView {
    fun showLoading()
    fun hideLoading()
    fun showError(errorId: Int)
}