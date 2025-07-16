package com.meta.metacomponents.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R

class MetaProgressView(context: Context, attrs: AttributeSet?):
    ProgressBar(context, attrs) {

    init {
        val progressLight = ContextCompat.getDrawable(context, R.drawable.progress_style_light)
        val progressDark = ContextCompat.getDrawable(context, R.drawable.progress_style_dark)


        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.MetaProgressView
        )

        this.max = 100
        val progress   = a.getInt(R.styleable.MetaProgressView_newProgress, 0)
        this.progress = progress

        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {   this.progressDrawable = progressDark }
            Configuration.UI_MODE_NIGHT_NO -> {  this.progressDrawable = progressLight}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {  this.progressDrawable = progressLight}
        }
    }

    fun setError(value: Int){
        val progressLight = ContextCompat.getDrawable(context, R.drawable.progress_style_error_light)
        this.progressDrawable = progressLight
        this.progress = progress

    }

}