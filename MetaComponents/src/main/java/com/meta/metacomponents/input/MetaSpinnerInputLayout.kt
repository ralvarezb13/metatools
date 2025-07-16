package com.meta.metacomponents.input

import android.content.Context
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Range
import android.util.TypedValue
import androidx.annotation.StringRes
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.meta.metacomponents.R


open class MetaSpinnerInputLayout : TextInputLayout {

    lateinit var editText: MaterialAutoCompleteTextView
    lateinit var newError: String

    constructor(context: Context) : super(context) {
        setup(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs) {
        setup(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        editText = MaterialAutoCompleteTextView(context)
        if (attrs != null) {
            val ta = context.theme.obtainStyledAttributes(attrs, R.styleable.MetaSpinnerInputLayout, 0, 0)

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_text)) {
                editText.setText(ta.getString(R.styleable.MetaSpinnerInputLayout_android_text))
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_idComponentS)) {
                editText.id = ta.getResourceId(R.styleable.MetaSpinnerInputLayout_idComponentS, 0)
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_textAllCaps)) {
                editText.isAllCaps =
                    ta.getBoolean(R.styleable.MetaSpinnerInputLayout_android_textAllCaps, false)
            }
            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_textColor)) {
                editText.setTextColor(
                    ta.getColor(
                        R.styleable.MetaSpinnerInputLayout_android_textColor,
                        0
                    )
                )
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_textSize)) {
                editText.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    ta.getDimensionPixelSize(R.styleable.MetaSpinnerInputLayout_android_textSize, 0)
                        .toFloat()
                )
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_inputType)) {
                editText.inputType = ta.getInt(R.styleable.MetaSpinnerInputLayout_android_inputType, -1)
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_focusable)) {
                editText.isFocusable = ta.getBoolean(R.styleable.MetaSpinnerInputLayout_android_focusable, true)
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_clickable)) {
                editText.isClickable = ta.getBoolean(R.styleable.MetaSpinnerInputLayout_android_clickable, false)
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_imeOptions)) {
                editText.imeOptions = ta.getInt(R.styleable.MetaSpinnerInputLayout_android_imeOptions, -1)
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_maxLines)) {
                editText.maxLines = ta.getInt(R.styleable.MetaSpinnerInputLayout_android_maxLines, 1)
            }


            newError = ta.getString(R.styleable.MetaSpinnerInputLayout_newErrorS).toString()


            val fontName = when (ta.getInteger(R.styleable.MetaSpinnerInputLayout_fontInputNameS, 0)) {
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
            editText.typeface = font

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_android_maxLength)) {
                lengthMax = ta.getInt(R.styleable.MetaSpinnerInputLayout_android_maxLength, 0)
                editText.filters = arrayOf<InputFilter>(LengthFilter(lengthMax))
            }

            if (ta.hasValue(R.styleable.MetaSpinnerInputLayout_isOptionalS)) {
                isOptional = ta.getBoolean(R.styleable.MetaSpinnerInputLayout_isOptionalS, false)
            }


            ta.recycle()
        }
        addView(editText)
    }

    var text: String?
        get() = editText.text.toString()
        set(text) {
            editText.setText(text)
        }

    fun setError(@StringRes errorRes: Int) {
        error = context.getString(errorRes)
    }

    override fun setHint(@StringRes hintRes: Int) {
        hint = context.getString(hintRes)
    }

    fun addTextChangedListener(textWatcher: TextWatcher?) {
        editText.addTextChangedListener(textWatcher)
    }

    fun removeTextChangedListener(textWatcher: TextWatcher?) {
        editText.removeTextChangedListener(textWatcher)
    }

    /*Sep meta components*/

    var type: String = ""
    var isOptional: Boolean = false
    var initialValue: String = ""
    var format: String = ""

    var lengthMax: Int = 0
        set(value: Int) {
            field = value
            editText.filters = arrayOf<InputFilter>(LengthFilter(value)) }

    var lengthMin: Int = 0

    fun isEmptyEditText(): Boolean{
        if(editText?.text?.toString().isNullOrEmpty()){
            showError()
            return false
        }
        return true
    }

    fun validateRange(): Boolean{
        if(!Range(lengthMin, lengthMax).contains(((editText.text?.count()) as Int))){
            newError = "Este campo no es válido"
            showError()
            return false
        }
        return true
    }
    fun showError(){
        this.error = newError
        this.editText.requestFocusFromTouch()
    }


    fun validateComponet(): Boolean{
        if(!isOptional){
            if(!this.isEmptyEditText()){ return false}
            if(!this.validateRange()){ return false}
        }else{
            if(editText.text?.isNotEmpty() == true){
                if(!this.validateRange()){ return false}
            }
        }
        this.error = ""
        return true
    }

}
