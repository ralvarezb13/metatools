package com.meta.metacomponents.views

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R
import com.meta.metacomponents.textview.NormalTextView

class StepToStepView: ConstraintLayout {

    private lateinit var root: View
    private lateinit var title: NormalTextView
    private lateinit var progressStep: ProgressBar

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
        root = LayoutInflater.from(context).inflate(R.layout.step_to_step_view, this)

        val container       = root.findViewById<View>(R.id.containerStep)
        title               = root.findViewById(R.id.sepTextView)
        progressStep        = root.findViewById(R.id.progressStep)

        val colorLight = ContextCompat.getColor(context, R.color.Blanco)
        val colorDark = ContextCompat.getColor(context, R.color.Bajio)
        val progressLight = ContextCompat.getDrawable(context, R.drawable.progress_style_light)
        val progressDark = ContextCompat.getDrawable(context, R.drawable.progress_style_dark)
        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                progressStep.progressDrawable = progressDark
                container.setBackgroundColor(colorDark) }
            Configuration.UI_MODE_NIGHT_NO -> {
                progressStep.progressDrawable = progressLight
                container.setBackgroundColor(colorLight) }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                progressStep.progressDrawable = progressLight
                container.setBackgroundColor(colorLight) }
        }

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.StepToStepView
        )

        val txtTitle    = a.getString(R.styleable.StepToStepView_titleStep)
        val progress   = a.getInt(R.styleable.StepToStepView_progressStep, 0)

        title.text = txtTitle
        progressStep.progress = progress

        a.recycle()

    }

    fun setParamsView(first: Int, last: Int){
        title.text = resources.getString(R.string.steps_text,first,last)
    }

    fun setProgress(progress: Int){
        progressStep.progress = progress
    }
}