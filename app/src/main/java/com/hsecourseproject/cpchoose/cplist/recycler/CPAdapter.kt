package com.hsecourseproject.cpchoose.cplist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO

class CPAdapter : RecyclerView.Adapter<CPViewHolder>() {

    private var courseProjects = listOf<CourseProjectDTO>()

    fun bindCourseProjects(courseProjects: List<CourseProjectDTO>) {
        this.courseProjects = courseProjects
    }

    private fun getItem(position: Int): CourseProjectDTO = courseProjects[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CPViewHolder {
        return CPViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cp_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CPViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = courseProjects.size


}


