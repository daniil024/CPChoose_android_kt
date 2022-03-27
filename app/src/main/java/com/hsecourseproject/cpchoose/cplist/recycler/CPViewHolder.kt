package com.hsecourseproject.cpchoose.cplist.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO

class CPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.cpCardTitle)
    private val status: TextView = itemView.findViewById(R.id.cpCardStatus)
    private val shortDescription: TextView = itemView.findViewById(R.id.cpCardShortDesc)
    private val mentor: TextView = itemView.findViewById(R.id.cpCardCreator)

    fun onBind(cp: CourseProjectDTO) {
        title.text = cp.titleRus
        status.text = cp.status?.name
        shortDescription.text = cp.annotation
        val mentorString = "Руководитель: " + cp.mentorFullName
        mentor.text = mentorString
    }
}