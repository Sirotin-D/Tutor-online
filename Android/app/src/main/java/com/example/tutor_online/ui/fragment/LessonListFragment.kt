package com.example.tutor_online.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutor_online.R
import com.example.tutor_online.databinding.LessonListFragmentBinding
import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.UserType
import com.example.tutor_online.datamodel.viewdatamodel.LessonListViewDataModel
import com.example.tutor_online.ui.activity.AuthActivity
import com.example.tutor_online.ui.activity.MainActivity
import com.example.tutor_online.ui.fragment.adapter.LessonListAdapter
import com.example.tutor_online.utils.datastorage.DataRepository
import com.example.tutor_online.viewmodel.LessonListViewModel

class LessonListFragment: Fragment(), IBaseView, OnItemClickListener {
    private var _binding: LessonListFragmentBinding? = null
    private val binding: LessonListFragmentBinding get() =_binding!!

    private lateinit var viewModel: LessonListViewModel

    private var _adapter: LessonListAdapter? = null
    private val adapter: LessonListAdapter get() = _adapter!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[LessonListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LessonListFragmentBinding.inflate(inflater, container, false)
        _adapter = LessonListAdapter()
        binding.lessonListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val inputMethodManager =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(recyclerView.windowToken, 0)
            }
        })
        val recyclerView = binding.lessonListRecyclerView
        recyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        binding.lessonListRecyclerView.layoutManager = mLayoutManager
        binding.lessonListRecyclerView.adapter = adapter
        binding.createLessonButton.visibility = View.GONE
        binding.createLessonButton.setOnClickListener {
            createLessonButtonPressed()
        }
        adapter.mItemClickListener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            lessonsListLiveData.observe(viewLifecycleOwner) {
                onLessonsListChanged(it)
            }
            lessonsListStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    LessonListViewDataModel.SHOW_LOADING -> {
                        showLoading()
                    }
                    LessonListViewDataModel.HIDE_LOADING -> {
                        hideLoading()
                    }
                    LessonListViewDataModel.SHOW_ERROR -> {
                       showError(it.errorMessage.toString())
                    }
                    LessonListViewDataModel.OPEN_LESSON -> {
                        findNavController().navigate(R.id.createLessonFragment)
                    }
                    else -> {}
                }
            }
            viewOpened()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }

    private fun onLessonsListChanged(list: List<Lesson>?) {
        if (list.isNullOrEmpty()) {
            adapter.setLessonList(listOf())
            binding.emptyLessonListTextView.visibility = View.VISIBLE
        } else {
            adapter.setLessonList(list)
            binding.emptyLessonListTextView.visibility = View.GONE
            if (DataRepository(context).getUserData().type == UserType.TUTOR.name) {
                binding.createLessonButton.visibility = View.VISIBLE
            }
        }
    }

    override fun showLoading() {
        binding.lessonListProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.lessonListProgressBar.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        binding.lessonListProgressBar.visibility = View.GONE
        val activity = activity as MainActivity
        activity.createNotification(errorMessage)
    }

    override fun onItemClick(position: Int) {
        val currentLesson = viewModel.lessonsListLiveData.value?.get(position)
        if (currentLesson != null) {
            val lessonId = currentLesson.lessonId
            val lessonTitle = currentLesson.title
            val lessonDescription = currentLesson.description
            val lessonTutorName = currentLesson.tutorName
            val lessonTutorId = currentLesson.tutorId
            val lessonWelcomeMessage = currentLesson.welcomeMessage
            val bundle = Bundle()
            bundle.putInt("lesson_id", lessonId)
            bundle.putString("lesson_title", lessonTitle)
            bundle.putString("lesson_description", lessonDescription)
            bundle.putString("lesson_tutor_name", lessonTutorName)
            bundle.putInt("lesson_tutor_id", lessonTutorId)
            bundle.putString("lesson_welcome_message", lessonWelcomeMessage)
            findNavController().navigate(R.id.lessonFragment, bundle)
        }
    }

    private fun createLessonButtonPressed() {
        findNavController().navigate(R.id.createLessonFragment)
    }
}