package com.meta.metatools.activitiesorfragments.signature

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.meta.metacomponents.databinding.FragmentMetaSignatureBinding
import com.meta.metatools.R
import com.meta.metatools.convertBitmapToBase64
import me.panavtec.drawableview.DrawableViewConfig
import kotlin.math.roundToInt

const val SIGNATURE_PARAM = "signature_param"

class SignatureFragment : Fragment() {

    private lateinit var fragmentSignatureBinding: FragmentMetaSignatureBinding
    var initBitmap: Bitmap? = null
    var signatureAction: SignatureAction? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.signature_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSignatureBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_meta_signature, container, false)
        val root = fragmentSignatureBinding.root
        val toolBar = fragmentSignatureBinding.toolbar
        toolBar.inflateMenu(R.menu.signature_menu)
        toolBar.title = resources.getString(R.string.enter_your_signature)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolBar)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.undo_signature -> fragmentSignatureBinding.signatureView.undo()
                R.id.clear_signature -> fragmentSignatureBinding.signatureView.clear()
                R.id.confirm_signature -> {
                    if (validateFields()) {
                        val base64Signature = fragmentSignatureBinding
                            .signatureView
                            .obtainBitmap().convertBitmapToBase64(requireContext()) ?: ""
                        signatureAction?.getSignature(base64Signature)
                    }
                }
                android.R.id.home -> requireActivity().onBackPressed()
            }
            false
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSignatureBinding.signatureView.setConfig(drawableViewConfig())
        initBitmap = fragmentSignatureBinding.signatureView.obtainBitmap()
        requireActivity().apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })
        }

        fragmentSignatureBinding.signatureSlider.addOnChangeListener { _, value, _ ->
            fragmentSignatureBinding.signatureLayout.scrollTo(value.roundToInt() * 100, 0)
        }
    }

    fun validateFields(): Boolean {
        if (fragmentSignatureBinding.signatureView.obtainBitmap().sameAs(initBitmap)) {
            fragmentSignatureBinding.signatureErrorText.visibility = View.VISIBLE
            return false
        }
        return true
    }

}

fun drawableViewConfig(): DrawableViewConfig {
    val config = DrawableViewConfig()
    config.strokeColor = Color.BLACK
    config.isShowCanvasBounds = false
    config.strokeWidth = 5.0f
    config.minZoom = 1.0f
    config.maxZoom = 1.0f
    config.canvasHeight = 1200
    config.canvasWidth = 2600
    return config
}

