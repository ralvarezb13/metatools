package com.meta.metacomponents.input

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodSubtype

class SimpleTextInputLayout(context: Context, attrs: AttributeSet?) :
    MetaInputLayout(context, attrs) {

    init {
        editText.inputType = InputType.TYPE_CLASS_TEXT
    }
}