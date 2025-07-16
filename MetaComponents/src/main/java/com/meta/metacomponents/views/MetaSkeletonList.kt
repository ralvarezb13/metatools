package com.meta.metacomponents.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.meta.metacomponents.R

class MetaSkeletonList : ConstraintLayout {

    private lateinit var root: View

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        attrs?.let { init(context, it) }
    }

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        attrs?.let { init(context, it) }
    }

    private fun init(context: Context, attrs: AttributeSet) {
        root = LayoutInflater.from(context).inflate(R.layout.skeleton_list, this)
    }
}