package com.example.tutor_online.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tutor_online.R

class LessonListFragment: Fragment(), IBaseView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.lessons_list_fragment, container, false)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorId: Int?) {

    }
}