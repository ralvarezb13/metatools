package com.meta.metacomponents.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.meta.metacomponents.R
import com.meta.metacomponents.textview.NormalTextView

class NoDataSimpleView: ConstraintLayout {

    private lateinit var root: View
    lateinit var imageViewNoData: ImageView

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        attrs?.let { init(context, it) }
    }

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        attrs?.let { init(context, it) }
    }

    private fun init(context: Context, attrs: AttributeSet) {
        root = LayoutInflater.from(context).inflate(R.layout.no_data_simple_view, this)
        val title       = root.findViewById<NormalTextView>(R.id.title_text_view)
        imageViewNoData       = root.findViewById(R.id.no_data_image_view)
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.NoDataSimpleView
        )

        val txtTitle    = a.getString(R.styleable.NoDataSimpleView_titleNoDataSimple)
        title.text = txtTitle

        if (a.hasValue(R.styleable.NoDataSimpleView_android_src)) {
            val id = a.getResourceId(R.styleable.NoDataSimpleView_android_src, R.drawable.ic_holder)
            imageViewNoData.setImageResource(id)
        }

        a.recycle()

    }

}