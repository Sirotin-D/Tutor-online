package com.example.tutor_online.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.databinding.LessonFragmentBinding
import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.viewdatamodel.LessonViewDataModel
import com.example.tutor_online.ui.activity.MainActivity
import com.example.tutor_online.utils.datastorage.DataRepository
import com.example.tutor_online.viewmodel.LessonViewModel

class LessonFragment : Fragment(), IBaseView {
    private var _binding: LessonFragmentBinding? = null
    private val binding: LessonFragmentBinding get() = _binding!!
    private lateinit var viewModel: LessonViewModel
    private lateinit var mLesson: Lesson

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
        val lessonId = requireArguments().getInt("lesson_id")
        val title = requireArguments().getString("lesson_title")
        val description = requireArguments().getString("lesson_description")
        val tutorName = requireArguments().getString("lesson_tutor_name")
        val tutorId = requireArguments().getInt("lesson_tutor_id")
        val welcomeMessage = requireArguments().getString("lesson_welcome_message")

        mLesson = Lesson(
            lessonId,
            tutorId,
            tutorName!!,
            title!!,
            welcomeMessage!!,
            description!!
        )
        binding.requestLessonButton.visibility = View.GONE
        binding.requestLessonButton.setOnClickListener {
            val userId = DataRepository(context).getUserData().id
            viewModel.handleRequestButtonClick(userId, mLesson.lessonId)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            lessonStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    LessonViewDataModel.INITIAL_STATE -> {
                        binding.lessonTitleTextView.text = mLesson.title
                        binding.tutorNameTextView.text = mLesson.tutorName
                        binding.lessonDescriptionTextView.text = mLesson.description
                        if (DataRepository(context).getUserData().type == "STUDENT") {
                            binding.requestLessonButton.visibility = View.VISIBLE
                        }
                    }
                    LessonViewDataModel.SHOW_ERROR -> {
                        showError(it.errorMessage.toString())
                    }
                    LessonViewDataModel.LESSON_REQUESTED -> {
                        showLessonRequested()
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

    override fun showError(errorMessage: String) {
        val activity = activity as MainActivity
        activity.createNotification(errorMessage)
    }

    private fun showLessonRequested() {
        showError("Урок успешно создан")
    }
}