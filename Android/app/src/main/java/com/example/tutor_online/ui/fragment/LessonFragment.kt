package com.example.tutor_online.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.R
import com.example.tutor_online.databinding.LessonFragmentBinding
import com.example.tutor_online.datamodel.viewDataModel.LessonViewDataModel
import com.example.tutor_online.viewmodel.LessonViewModel

class LessonFragment : Fragment(), IBaseView {
    private var _binding: LessonFragmentBinding? = null
    private val binding: LessonFragmentBinding get() = _binding!!
    private lateinit var viewModel: LessonViewModel
    private var mLessonId: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[LessonViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LessonFragmentBinding.inflate(inflater, container, false)
        val lessonId = arguments?.getString("id")
        mLessonId = lessonId
        binding.cancelRequestLessonButton.visibility = View.GONE
        binding.requestLessonButton.setOnClickListener {
            Toast.makeText(context, R.string.success_request, Toast.LENGTH_LONG).show()
            binding.requestLessonButton.isEnabled = false
        }
        binding.cancelRequestLessonButton.setOnClickListener {
            Toast.makeText(context, R.string.cancelled_request, Toast.LENGTH_LONG).show()
            binding.cancelRequestLessonButton.isEnabled = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            lessonLiveData.observe(viewLifecycleOwner) {
                binding.lessonTitleTextView.text = it.title
                binding.tutorNameTextView.text = it.tutorName
                binding.lessonDescriptionTextView.text = it.description
            }

            lessonStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    LessonViewDataModel.SHOW_LOADING -> {

                    }
                    LessonViewDataModel.HIDE_LOADING -> {

                    }
                    LessonViewDataModel.SHOW_ERROR -> {

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