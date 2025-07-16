package com.meta.metacomponents.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R

class CircleBackground(context: Context, attrs: AttributeSet?):
    ConstraintLayout(context, attrs) {

    init {
        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> { setBackgroundContainer(R.drawable.circle_background_dark) }
            Configuration.UI_MODE_NIGHT_NO -> { setBackgroundContainer(R.drawable.circle_background_light) }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> { setBackgroundContainer(R.drawable.circle_background_light) }
        }
    }

    private fun setBackgroundContainer(drawable: Int) {
        this.background = ContextCompat.getDrawable(context, drawable)
    }
}