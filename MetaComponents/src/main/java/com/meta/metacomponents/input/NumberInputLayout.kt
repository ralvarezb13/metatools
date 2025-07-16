package com.meta.metacomponents.input

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo


class NumberInputLayout(context: Context, attrs: AttributeSet?) :
    MetaInputLayout(context, attrs) {

    init {
        editText.inputType = InputType.TYPE_CLASS_NUMBER
    }
}