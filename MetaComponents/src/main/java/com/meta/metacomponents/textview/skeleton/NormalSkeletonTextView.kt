package com.meta.metacomponents.textview.skeleton

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R

class NormalSkeletonTextView(context: Context, attrs: AttributeSet?) :
    MetaSkeletonTextView(context, attrs) {

    init {
        val size = (context.resources.getDimension(R.dimen.text_normal)/3)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                background = ContextCompat.getDrawable(context, R.color.Bajio_80p)
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                background = ContextCompat.getDrawable(context, R.color.Bajio_60p)
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                background = ContextCompat.getDrawable(context, R.color.Bajio_80p)
            }
        }

    }
}