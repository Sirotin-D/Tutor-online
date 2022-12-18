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
import com.example.tutor_online.datamodel.RequestLessonStatus
import com.example.tutor_online.datamodel.UserType
import com.example.tutor_online.datamodel.viewdatamodel.RequestLessonViewDataModel
import com.example.tutor_online.ui.activity.MainActivity
import com.example.tutor_online.utils.datastorage.DataRepository
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
        val requestId = arguments?.getInt("request_id")
        val requestLessonId = arguments?.getInt("request_lesson_id")
        val lessonTutorId = arguments?.getInt("request_tutor_id")
        val lessonWelcomeMessage = requireArguments().getString("request_welcome_message")
        val requestStudentId = arguments?.getInt("request_student_id")
        val requestStatus = requireArguments().getString("request_status")
        mCurrentRequestLesson = RequestLesson(
            requestId!!,
            requestLessonId!!,
            lessonTutorId!!,
            requestStudentId!!,
            requestStatus!!,
            lessonWelcomeMessage!!
        )

        binding.cancelButton.visibility = View.GONE
        binding.acceptButton.visibility = View.GONE
        binding.deleteButton.visibility = View.GONE


        binding.cancelButton.setOnClickListener {
            binding.lessonStatusTextView.text = "Урок отменён"
            viewModel.handleCancelButtonClick(
                mCurrentRequestLesson.lessonId)
        }

        binding.acceptButton.setOnClickListener {
            binding.lessonStatusTextView.text = "Урок принят"
            viewModel.handleAcceptButtonClick(
                mCurrentRequestLesson.lessonId)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.handleDeleteButtonClick(
                mCurrentRequestLesson.lessonId)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUserType = DataRepository(context).getUserData().type
        viewModel.apply {
            requestLessonStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    RequestLessonViewDataModel.SHOW_LOADING -> {
                        showLoading()
                    }
                    RequestLessonViewDataModel.HIDE_LOADING -> {
                        hideLoading()
                    }
                    RequestLessonViewDataModel.SHOW_PENDING_LESSON_FOR_STUDENT -> {
                        hideLoading()
                        binding.lessonTitleTextView.text = it.lesson?.title
                        binding.tutorNameTextView.text = it.lesson?.tutorName
                        binding.lessonDescriptionTextView.text = it.lesson?.description
                        binding.lessonStatusTextView.text = RequestLessonStatus.PENDING.toString()
                        binding.lessonWelcomeMessage.visibility = View.GONE
                        binding.cancelButton.visibility = View.VISIBLE
                    }
                    RequestLessonViewDataModel.SHOW_PENDING_LESSON_FOR_TUTOR -> {
                        hideLoading()
                        binding.lessonTitleTextView.text = it.lesson?.title
                        binding.tutorNameTextView.text = it.lesson?.tutorName
                        binding.lessonDescriptionTextView.text = it.lesson?.description
                        binding.lessonStatusTextView.text = RequestLessonStatus.PENDING.toString()
                        binding.lessonWelcomeMessage.text = mCurrentRequestLesson.welcomeMessage
                        binding.deleteButton.visibility = View.VISIBLE
                        binding.cancelButton.visibility = View.VISIBLE
                        binding.acceptButton.visibility = View.VISIBLE
                    }
                    RequestLessonViewDataModel.SHOW_ACCEPTED_LESSON -> {
                        hideLoading()
                        binding.lessonTitleTextView.text = it.lesson?.title
                        binding.tutorNameTextView.text = it.lesson?.tutorName
                        binding.lessonDescriptionTextView.text = it.lesson?.description
                        binding.lessonStatusTextView.text = RequestLessonStatus.ACCEPTED.toString()
                        binding.lessonWelcomeMessage.text = mCurrentRequestLesson.welcomeMessage
                    }
                    RequestLessonViewDataModel.SHOW_CANCELLED_LESSON -> {
                        hideLoading()
                        binding.lessonTitleTextView.text = it.lesson?.title
                        binding.tutorNameTextView.text = it.lesson?.tutorName
                        binding.lessonDescriptionTextView.text = it.lesson?.description
                        if (currentUserType == UserType.TUTOR.name) {
                            binding.lessonWelcomeMessage.text = mCurrentRequestLesson.welcomeMessage
                        }
                        binding.lessonStatusTextView.text = RequestLessonStatus.CANCELLED.toString()
                    }
                    RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED -> {
                        hideLoading()
                        binding.cancelButton.visibility = View.GONE
                        binding.acceptButton.visibility = View.GONE
                        binding.deleteButton.visibility = View.GONE
                        showMessageAfterButtonPressed()
                    }
                    RequestLessonViewDataModel.SHOW_ERROR -> {
                        hideLoading()
                        showError(it.errorMessage.toString())
                    }
                    else -> {}
                }
            }
            viewOpened(mCurrentRequestLesson, currentUserType)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showLoading() {
        binding.requestLessonProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.requestLessonProgressBar.visibility = View.GONE
    }

    private fun showMessageAfterButtonPressed() {
        showError("Статус успешно изменён")
    }

    override fun showError(errorMessage: String) {
        val activity = activity as MainActivity
        activity.createNotification(errorMessage)
    }
}