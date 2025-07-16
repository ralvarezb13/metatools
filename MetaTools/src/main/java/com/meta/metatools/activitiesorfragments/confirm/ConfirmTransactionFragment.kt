package com.meta.metatools.activitiesorfragments.confirm

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.bumptech.glide.Glide
import com.meta.metacomponents.databinding.FragmentConfirmTransactionBinding
import com.meta.metatools.R
import com.meta.metatools.activitiesorfragments.camera.BACK_CAMERA
import com.meta.metatools.activitiesorfragments.camera.CameraActivity
import com.meta.metatools.models.ConfirmConfigModel

const val CONFIRM_CONFIG_MODEL = "confirm_config_model"

class ConfirmTransactionFragment : Fragment() {

    private lateinit var launchCamera: ActivityResultLauncher<Intent>

    private var binding: FragmentConfirmTransactionBinding? = null

    private var confirmAdapter: ConfirmAdapter? = null

    private var confirmConfigModel: ConfirmConfigModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            confirmConfigModel = it.getParcelable(CONFIRM_CONFIG_MODEL) as? ConfirmConfigModel
        }
        launchCamera =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                confirmConfigModel?.dataRequest?.invoke(activityResult?.data)
            }
        if (confirmConfigModel?.reviewImage == null) {
            if (confirmConfigModel?.skipAdvice == true) {
                launchCamera()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_confirm_transaction,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmAdapter = ConfirmAdapter()
        binding?.apply {
            confirmConfigModel?.headerText?.let {
                headerLabel.text = it
            }
            confirmConfigModel?.headerImageIcon?.let {
                headerImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), it
                    )
                )
            }
            confirmConfigModel?.listConfirmModel?.let {
                confirmAdapter?.addItems(it)
            }
            confirmConfigModel?.filePathImage?.let { filePath ->
                Glide.with(requireContext()).load(filePath).into(headerImage)
               // headerImage.load(filePath)
            }
            confirmList.apply {
                adapter = confirmAdapter
                layoutManager = LinearLayoutManager(requireActivity())
                setHasFixedSize(true)
            }
            rightButton.setOnClickListener {
                if (confirmConfigModel?.openBackCamera != null) {
                    launchCamera()
                } else {
                    arguments?.clear()
                    imageBitmap = null
                    var base64 = ""
                    confirmConfigModel?.filePathImage?.let { filePath ->
                        base64 = filePath
                    }
                    confirmConfigModel?.actionRight?.invoke(base64)
                    confirmConfigModel = null
                }
            }

            if (!confirmConfigModel?.rightButtonText.isNullOrEmpty()) {
                rightButton.text = confirmConfigModel?.rightButtonText
            }

            if (!confirmConfigModel?.leftButtonText.isNullOrEmpty()) {
                leftButton.visibility = View.VISIBLE
            }
            leftButton.text = confirmConfigModel?.leftButtonText

            leftButton.setOnClickListener {
                if (confirmConfigModel?.openBackCamera != null) {
                    launchCamera()
                } else {
                    arguments?.clear()
                    imageBitmap = null
                    confirmConfigModel?.actionLeft?.invoke()
                    confirmConfigModel = null
                }
            }
            if (confirmConfigModel?.checkVisibility == true) {
                chkNoShow.visibility = View.VISIBLE
            }

            if (confirmConfigModel?.confirmVisibility == true) {
                rightButton.visibility = View.INVISIBLE
                confirmButton.visibility = View.VISIBLE
            }

            var base64 = ""
            confirmConfigModel?.filePathImage?.let { filePath ->
                base64 = filePath
            }
            confirmButton.setOnClickListener {
                confirmConfigModel?.actionRight?.invoke(base64)
            }
            chkNoShow.setOnCheckedChangeListener { _, isSecurity ->
                confirmConfigModel?.actionCheck?.invoke(isSecurity)
            }

            imageBitmap?.let {
                headerImage.setImageBitmap(it)
            }

            confirmButton.visibility = View.GONE
            chkNoShow.visibility = View.GONE
            rightButton.visibility = View.GONE
            leftButton.visibility = View.GONE
        }
    }

    private fun launchCamera() {
        val intent = Intent(context, CameraActivity::class.java)
        intent.putExtra(BACK_CAMERA, confirmConfigModel?.openBackCamera)
        launchCamera.launch(intent)
    }

    companion object {
        var imageBitmap: Bitmap? = null
    }


}