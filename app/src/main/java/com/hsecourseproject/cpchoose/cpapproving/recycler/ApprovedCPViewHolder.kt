package com.hsecourseproject.cpchoose.cpapproving.recycler

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.models.ApprovedCPDTO

class ApprovedCPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val approvedCPTitle: TextView = itemView.findViewById(R.id.cpApprovingCardTitle)
    private val shortDesc: TextView = itemView.findViewById(R.id.cpApprovingCardShortDesc)
    private val student: TextView = itemView.findViewById(R.id.cpApprovingCardStudent)
    private val acceptBtn: Button = itemView.findViewById(R.id.cpApprovingBtn)
    private val declineBtn: Button = itemView.findViewById(R.id.cpDecliningBtn)

    fun onBind(
        approvedCPDTO: ApprovedCPDTO,
        clickListener:ClickListener
    ) {
        approvedCPTitle.text = approvedCPDTO.courseProject?.titleRus
        shortDesc.text = approvedCPDTO.courseProject?.annotation
        val studentText =
            "Студент: ${approvedCPDTO.student?.firstName} ${approvedCPDTO.student?.lastName} " +
                    "${approvedCPDTO.student?.patronymic}"
        this.student.text = studentText

        acceptBtn.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onAcceptClicked(approvedCPDTO)
            }
        }
        declineBtn.setOnClickListener{
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onDeclineClicked(approvedCPDTO)
            }
        }
    }
}