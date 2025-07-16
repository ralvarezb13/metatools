package com.meta.metacomponents.textview.skeleton

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.meta.metacomponents.R


open class MetaSkeletonTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {

    init {

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.MetaTextView
        )

        val fontName: Int = when (a.getInteger(R.styleable.MetaTextView_fontName, 0)) {
            1 -> R.string.Roboto_Bold
            2 -> R.string.Roboto_Italic
            3 -> R.string.Roboto_Light
            4 -> R.string.Roboto_Medium
            5 -> R.string.Roboto_Regular
            else -> R.string.Roboto_Regular
        }
        val customFont = resources.getString(fontName)
        val font = Typeface.createFromAsset(
            context.assets,
            customFont
        )
        typeface = font
        a.recycle()
    }
}
