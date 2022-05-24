package com.example.apod.component.layoutmanager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OverScrollGridLayoutManager : GridLayoutManager {

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, spanCount: Int) : super(context, spanCount)
    constructor(
        context: Context?,
        spanCount: Int,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(context, spanCount, orientation, reverseLayout)

    private var overScrollListener: ((overScrollBottom: Boolean) -> Unit)? = null

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrollRange = super.scrollVerticallyBy(dy, recycler, state)
        val overScroll: Int = dy - scrollRange
        val overScrollBottom = overScroll > 0
        overScrollListener?.invoke(overScrollBottom)
        return scrollRange
    }

    fun setOverScrollListener(listener: ((overScrollBottom: Boolean) -> Unit)) {
        overScrollListener = listener
    }

}