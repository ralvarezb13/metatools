package com.meta.metacomponents.textview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import com.meta.metacomponents.R


class ExtraExtraLargeTextView(context: Context, attrs: AttributeSet?) :
    MetaTextView(context, attrs) {

    init {
        val size = (context.resources.getDimension(R.dimen.text_extra_extra_large)/3)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }
}