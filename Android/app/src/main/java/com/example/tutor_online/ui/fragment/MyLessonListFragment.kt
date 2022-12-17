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
import com.example.tutor_online.databinding.MyLessonListFragmentBinding
import com.example.tutor_online.datamodel.RequestLesson
import com.example.tutor_online.datamodel.viewDataModel.LessonListViewDataModel
import com.example.tutor_online.ui.fragment.adapter.MyLessonListAdapter
import com.example.tutor_online.viewmodel.MyLessonListViewModel

class MyLessonListFragment: Fragment(), IBaseView, OnItemClickListener {
    private var _binding: MyLessonListFragmentBinding? = null
    private val binding: MyLessonListFragmentBinding get() =_binding!!

    private lateinit var viewModel: MyLessonListViewModel

    private var _adapter: MyLessonListAdapter? = null
    private val adapter: MyLessonListAdapter get() = _adapter!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[MyLessonListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyLessonListFragmentBinding.inflate(inflater, container, false)
        _adapter = MyLessonListAdapter()
        binding.myLessonListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val inputMethodManager =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(recyclerView.windowToken, 0)
            }
        })
        val recyclerView = binding.myLessonListRecyclerView
        recyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        binding.myLessonListRecyclerView.layoutManager = mLayoutManager
        binding.myLessonListRecyclerView.adapter = adapter
        adapter.mItemClickListener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            myLessonsListLiveData.observe(viewLifecycleOwner) {
                onLessonsListChanged(it)
            }
            myLessonsListStateLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    LessonListViewDataModel.SHOW_LOADING -> {
                        showLoading()
                    }
                    LessonListViewDataModel.HIDE_LOADING -> {
                        hideLoading()
                    }
                    LessonListViewDataModel.SHOW_ERROR -> {
                        showError(it.resourceId)
                    }
                    LessonListViewDataModel.OPEN_LESSON -> {

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

    private fun onLessonsListChanged(list: List<RequestLesson>?) {
        if (list.isNullOrEmpty()) {
            adapter.setLessonList(listOf())
            binding.emptyMyLessonListTextView.visibility = View.VISIBLE
        } else {
            adapter.setLessonList(list)
            binding.emptyMyLessonListTextView.visibility = View.GONE
        }
    }

    override fun showLoading() {
        binding.myLessonListProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.myLessonListProgressBar.visibility = View.GONE
    }

    override fun showError(errorId: Int?) {

    }

    override fun onItemClick(id: String) {
        val bundle = Bundle()
        bundle.putString("request_lesson_id", id)
        findNavController().navigate(R.id.lessonFragment, bundle)
    }
}