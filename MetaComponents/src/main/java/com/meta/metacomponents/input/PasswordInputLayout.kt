package com.meta.metacomponents.input

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo


class PasswordInputLayout(context: Context, attrs: AttributeSet?) :
    MetaInputLayout(context, attrs) {

    init {
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }
}