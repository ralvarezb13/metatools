package com.meta.metacomponents.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R

class MetaConstrainsView(context: Context, attrs: AttributeSet?):
    ConstraintLayout(context, attrs) {

    init {
        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> { setBackgroundContainer(R.color.Bajio_Dark) }
            Configuration.UI_MODE_NIGHT_NO -> { setBackgroundContainer(R.color.Bajio_20p)}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> { setBackgroundContainer(R.color.Bajio_20p)}
        }
    }

    private fun setBackgroundContainer(color: Int){
        this.setBackgroundColor(ContextCompat.getColor(context, color))
    }
}