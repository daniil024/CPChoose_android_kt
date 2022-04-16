package com.hsecourseproject.cpchoose.cplist.recycler

import androidx.recyclerview.widget.DiffUtil
import com.hsecourseproject.cpchoose.models.CourseProjectDTO

class CPCallback(
    private val oldList: List<CourseProjectDTO>,
    private val newList: List<CourseProjectDTO>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}