package com.hsecourseproject.cpchoose.cpapproving.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ApprovedCPItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position: Int = parent.getChildLayoutPosition(view)
        if (position != RecyclerView.NO_POSITION) {
            outRect.set(offset, offset, offset, offset)
        } else {
            outRect.set(0, 0, 0, 0)
        }
    }
}