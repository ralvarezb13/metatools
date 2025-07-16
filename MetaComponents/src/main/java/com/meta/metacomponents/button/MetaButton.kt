package com.meta.metacomponents.button

import android.content.Context
import android.content.res.Configuration
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.meta.metacomponents.R

class MetaButton(context: Context, attrs: AttributeSet?) : AppCompatButton(context, attrs) {

    init {

        val ta = context.obtainStyledAttributes(
            attrs,
            R.styleable.MetaButton
        )

        val fontName = when (ta.getInteger(R.styleable.MetaButton_fontButtonName, 0)) {
            1 -> R.string.Roboto_Bold
            2 -> R.string.Roboto_Italic
            3 -> R.string.Roboto_Light
            4 -> R.string.Roboto_Medium
            5 -> R.string.Roboto_Regular
            else -> R.string.Roboto_Bold
        }
        val customFont = resources.getString(fontName)
        val font = Typeface.createFromAsset(
            context.assets,
            customFont
        )
        typeface = font
        textSize = 14f

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


        ta.recycle()
    }

    private fun modeLight() {
        setTextColor(ContextCompat.getColor(context, R.color.Blanco))
        background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_light, null)

    }

    private fun modeDark() {
        setTextColor(ContextCompat.getColor(context, R.color.Bajio))
        background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_dark, null)

    }
}