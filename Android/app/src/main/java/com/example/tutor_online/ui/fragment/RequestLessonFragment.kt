package com.example.tutor_online.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.databinding.RequestLessonFragmentBinding
import com.example.tutor_online.datamodel.RequestLesson
import com.example.tutor_online.datamodel.viewDataModel.RequestLessonViewDataModel
import com.example.tutor_online.viewmodel.RequestLessonViewModel

class RequestLessonFragment: Fragment(), IBaseView {
    private var _binding: RequestLessonFragmentBinding? = null
    private val binding: RequestLessonFragmentBinding get() = _binding!!
    private lateinit var viewModel: RequestLessonViewModel
    private lateinit var mCurrentRequestLesson: RequestLesson

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[RequestLessonViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RequestLessonFragmentBinding.inflate(inflater, container, false)
        val requestId = requireArguments().getString("request_id")
        val requestLessonId = requireArguments().getString("request_lesson_id")
        val lessonTitle = requireArguments().getString("request_lesson_title")
        val lessonDescription = requireArguments().getString("request_lesson_description")
        val lessonTutorName = requireArguments().getString("request_lesson_tutor_name")
        val lessonTutorId = requireArguments().getString("request_tutor_id")
        val lessonWelcomeMessage = requireArguments().getString("request_welcome_message")
        val requestStudentId = requireArguments().getString("request_student_id")
        val requestStatus = requireArguments().getString("request_status")
        mCurrentRequestLesson = RequestLesson(
            requestId!!,
            requestLessonId!!,
            lessonTitle!!,
            lessonDescription!!,
            lessonTutorName!!,
            lessonTutorId!!,
            lessonWelcomeMessage!!,
            requestStudentId!!,
            requestStatus!!
        )

        binding.cancelButton.visibility = View.GONE
        binding.acceptButton.visibility = View.GONE
        binding.deleteButton.visibility = View.GONE


        binding.cancelButton.setOnClickListener {
            viewModel.handleCancelButtonClick(
                mCurrentRequestLesson.request_student_id,
                mCurrentRequestLesson.request_lesson_id)
        }

        binding.acceptButton.setOnClickListener {
            viewModel.handleAcceptButtonClick(
                mCurrentRequestLesson.request_student_id,
                mCurrentRequestLesson.request_lesson_id)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.handleDeleteButtonClick(
                mCurrentRequestLesson.request_student_id,
                mCurrentRequestLesson.request_lesson_id)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            requestLessonStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    RequestLessonViewDataModel.INITIAL_STATE -> {
                        binding.lessonTitleTextView.text = mCurrentRequestLesson.request_lesson_title
                        binding.tutorNameTextView.text = mCurrentRequestLesson.request_lesson_tutor_name
                        binding.lessonDescriptionTextView.text = mCurrentRequestLesson.request_lesson_description
                        binding.lessonStatusTextView.text = mCurrentRequestLesson.request_status
                        binding.lessonWelcomeMessage.text = mCurrentRequestLesson.request_welcome_message
                        binding.cancelButton.visibility = View.VISIBLE
                        binding.acceptButton.visibility = View.VISIBLE
                        binding.deleteButton.visibility = View.VISIBLE
                    }
                    else -> {}
                }
            }
            viewOpened()
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorId: Int?) {

    }
}