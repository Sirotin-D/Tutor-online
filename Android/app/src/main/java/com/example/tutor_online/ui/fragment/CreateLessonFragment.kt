package com.example.tutor_online.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutor_online.databinding.CreateLessonFragmentBinding
import com.example.tutor_online.datamodel.viewdatamodel.CreateLessonViewDataModel
import com.example.tutor_online.ui.activity.MainActivity
import com.example.tutor_online.utils.datastorage.DataRepository
import com.example.tutor_online.viewmodel.CreateLessonViewModel

class CreateLessonFragment: Fragment(), IBaseView {
    private var _binding: CreateLessonFragmentBinding? = null
    private val binding: CreateLessonFragmentBinding get() = _binding!!
    private lateinit var viewModel: CreateLessonViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[CreateLessonViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateLessonFragmentBinding.inflate(inflater, container, false)
        binding.acceptCreateButtonLesson.setOnClickListener {
            val tutor = DataRepository(context).getUserData()
            viewModel.createLessonButtonPressed(
                tutor,
                binding.lessonTitleEditText.text.toString(),
                binding.lessonDescriptionEditText.text.toString(),
                binding.lessonContactDataEditText.text.toString()
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            createLessonStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    CreateLessonViewDataModel.INITIAL_STATE -> {

                    }
                    CreateLessonViewDataModel.SHOW_ERROR -> {
                        showError(it.errorMessage.toString())
                    }
                    CreateLessonViewDataModel.LESSON_CREATED -> {
                        lessonCreated()
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

    private fun lessonCreated() {
        showError("Урок создан")
    }

    override fun showError(errorMessage: String) {
        val activity = activity as MainActivity
        activity.createNotification(errorMessage)
    }
}