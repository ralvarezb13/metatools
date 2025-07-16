package com.meta.metacomponents.textview

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R


class LinkTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {

    init {

        val size = (context.resources.getDimension(R.dimen.text_normal)/3)
        setTextColor(ContextCompat.getColor(context, R.color.Zacatecas))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size)

        linksClickable = true
        movementMethod = LinkMovementMethod.getInstance()
        paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.LinkTextView
        )

        var fontName: Int = when (a.getInteger(R.styleable.LinkTextView_fontLinkName, 0)) {
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
        val url = a.getString(R.styleable.LinkTextView_newLink)
        if(url != null){
            openNewURL(url)
        }

        typeface = font
        a.recycle()
    }


    fun openURL(url: String) {
        openNewURL(url)
    }

    private fun openNewURL(url: String){

        setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            context.startActivity(i)
        }

    }

}
