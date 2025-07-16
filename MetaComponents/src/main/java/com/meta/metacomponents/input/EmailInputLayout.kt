package com.meta.metacomponents.input

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo

class EmailInputLayout(context: Context, attrs: AttributeSet?) :
    MetaInputLayout(context, attrs) {

    init {
        editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }
}