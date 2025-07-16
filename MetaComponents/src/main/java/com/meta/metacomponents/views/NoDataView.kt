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

class NoDataView: ConstraintLayout {

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

    private fun init(context: Context,attrs: AttributeSet) {
        root = LayoutInflater.from(context).inflate(R.layout.no_data_view, this)

        val container   = root.findViewById<View>(R.id.container1)
        val title       = root.findViewById<NormalTextView>(R.id.title_text_view)
        val detail      = root.findViewById<NormalTextView>(R.id.detail_text_view)
        imageViewNoData       = root.findViewById(R.id.no_data_image_view)

        when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                container.background = ContextCompat.getDrawable(context, R.drawable.background_dark) }
            Configuration.UI_MODE_NIGHT_NO -> {
                container.background = ContextCompat.getDrawable(context, R.drawable.background_light)}
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                container.background = ContextCompat.getDrawable(context, R.drawable.background_light)}
        }

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.NoDataView
        )

        val txtTitle    = a.getString(R.styleable.NoDataView_titleNoData)
        val txtDetail   = a.getString(R.styleable.NoDataView_detailNoData)
        title.text = txtTitle
        detail.text = txtDetail
        if (a.hasValue(R.styleable.NoDataSimpleView_android_src)) {
            val id = a.getResourceId(R.styleable.NoDataSimpleView_android_src, R.drawable.ic_holder)
            imageViewNoData.setImageResource(id)
        }

        a.recycle()

    }
}