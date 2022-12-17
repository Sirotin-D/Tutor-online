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
import com.example.tutor_online.datamodel.viewDataModel.LessonViewDataModel
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
        val lessonId = requireArguments().getString("lesson_id")
        val lessonTitle = requireArguments().getString("lesson_title")
        val lessonDescription = requireArguments().getString("lesson_description")
        val lessonTutorName = requireArguments().getString("lesson_tutor_name")
        val lessonTutorId = requireArguments().getString("lesson_tutor_id")
        val lessonWelcomeMessage = requireArguments().getString("lesson_welcome_message")

        mLesson = Lesson(
            lessonId!!,
            lessonTitle!!,
            lessonDescription!!,
            lessonTutorName!!,
            lessonTutorId!!,
            lessonWelcomeMessage!!
        )
        binding.requestLessonButton.visibility = View.GONE
        binding.requestLessonButton.setOnClickListener {
            val userId = DataRepository(context).getUserData().user_id!!
            viewModel.handleRequestButtonClick(userId, mLesson.lesson_id)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            lessonStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    LessonViewDataModel.INITIAL_STATE -> {
                        binding.lessonTitleTextView.text = mLesson.lesson_title
                        binding.tutorNameTextView.text = mLesson.lesson_tutor_name
                        binding.lessonDescriptionTextView.text = mLesson.lesson_description
                        if (DataRepository(context).getUserData().user_type == "student") {
                            binding.requestLessonButton.visibility = View.VISIBLE
                        }
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