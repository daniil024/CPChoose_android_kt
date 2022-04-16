package com.hsecourseproject.cpchoose.cplist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.models.CourseProjectDTO

class CPAdapter(private val clickListener: OnRecyclerItemClicked) :
    RecyclerView.Adapter<CPViewHolder>() {

    private var courseProjects: List<CourseProjectDTO> = listOf()

    fun bindCourseProjects(cp: List<CourseProjectDTO>) {
        this.courseProjects = cp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CPViewHolder {
        return CPViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cp_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CPViewHolder, position: Int) {
        holder.onBind(courseProjects[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(courseProjects[position])
        }
    }

    override fun getItemCount(): Int = courseProjects.size
}

interface OnRecyclerItemClicked {
    fun onClick(cp: CourseProjectDTO)
}
