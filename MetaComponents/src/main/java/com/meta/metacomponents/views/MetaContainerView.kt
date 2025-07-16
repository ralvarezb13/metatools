package com.meta.metacomponents.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R
import android.util.TypedValue




class MetaContainerView(context: Context, attrs: AttributeSet?):
    ConstraintLayout(context, attrs) {

    init {
        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> { setBackgroundContainer(R.drawable.background_dark) }
            Configuration.UI_MODE_NIGHT_NO -> { setBackgroundContainer(R.drawable.background_light)}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> { setBackgroundContainer(R.drawable.background_light)}
        }

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.MetaContainerView
        )

        val padding = a.getInteger(R.styleable.MetaContainerView_newPadding, 16)
        val pdp = dp2px(padding)
        setPadding(pdp,0,pdp,0)
        a.recycle()
    }

    private fun setBackgroundContainer(drawable: Int){
        this.background = ContextCompat.getDrawable(context, drawable)
    }

    fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}