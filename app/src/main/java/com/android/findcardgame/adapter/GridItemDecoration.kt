package com.android.findcardgame.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(private var mSpanCount: Int, private var mSpacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {


        val position = parent.getChildAdapterPosition(view!!)
        val column = position % mSpanCount
        val row = parent.height / 4

            outRect.left = mSpacing - column * mSpacing / mSpanCount
            outRect.right = (column + 1) * mSpacing / mSpanCount
            if (position < mSpanCount) {
                outRect.top = mSpacing
            }
            outRect.bottom = mSpacing

    }



}
