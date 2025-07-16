package com.meta.metacomponents.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R

class MetaProgressIndeterminate(context: Context, attrs: AttributeSet?):
    ProgressBar(context, attrs) {

    init {
        val progressLight = ContextCompat.getDrawable(context, R.drawable.progress_style_light)
        val progressDark = ContextCompat.getDrawable(context, R.drawable.progress_style_dark)

        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {   this.indeterminateDrawable = progressDark }
            Configuration.UI_MODE_NIGHT_NO -> {  this.indeterminateDrawable = progressLight}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {  this.indeterminateDrawable = progressLight}
        }
    }

}