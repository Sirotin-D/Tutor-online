package com.example.tutor_online.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tutor_online.databinding.LessonItemBinding
import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.ui.fragment.OnItemClickListener

class LessonListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mItemClickListener: OnItemClickListener? = null
    private val mLessonList: MutableList<Lesson> = mutableListOf()

    fun setLessonList(items: List<Lesson>) {
        mLessonList.clear()
        mLessonList.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LessonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        val currentLesson = mLessonList[position]
        holder.itemView.setOnClickListener {
            mItemClickListener?.onItemClick(position)
        }
        itemViewHolder.bind(currentLesson)
    }

    override fun getItemCount(): Int {
        return mLessonList.size
    }

    inner class ItemViewHolder(private val binding: LessonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lessonItem: Lesson) {
            binding.lessonTitleTextView.text = lessonItem.title
            binding.lessonTutorName.text = "Преподаватель: ${lessonItem.tutorName}"
        }
    }
}