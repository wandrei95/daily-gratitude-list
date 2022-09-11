package com.andrei.wegroszta.dailygratitudelist.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val leftSpacing: Int = 0,
    private val topSpacing: Int = 0,
    private val rightSpacing: Int = 0,
    private val bottomSpacing: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapterPos = parent.getChildAdapterPosition(view)
        if (adapterPos != RecyclerView.NO_POSITION) {
            outRect.set(leftSpacing, topSpacing, rightSpacing, bottomSpacing)
        }
    }
}
