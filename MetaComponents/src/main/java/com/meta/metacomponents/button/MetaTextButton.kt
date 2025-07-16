package com.meta.metacomponents.button

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R
import com.meta.metacomponents.textview.MetaTextView

class MetaTextButton(context: Context, attrs: AttributeSet?) :
    MetaTextView(context, attrs) {

    init {
        val size = (context.resources.getDimension(R.dimen.text_normal) / 3)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        gravity = Gravity.CENTER

        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                modeDark()
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                modeLight()
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                modeLight()
            }
        }

    }

    private fun modeLight() {
        setTextColor(ContextCompat.getColor(context, R.color.Queretaro))

    }

    private fun modeDark() {
        setTextColor(ContextCompat.getColor(context, R.color.Aguascalientes))
    }
}