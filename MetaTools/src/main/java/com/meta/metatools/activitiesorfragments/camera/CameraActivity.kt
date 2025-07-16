package com.meta.metatools.activitiesorfragments.camera

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Size
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.meta.metatools.R
import java.io.File
import java.util.concurrent.Executors
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.meta.metacomponents.databinding.FragmentConfirmTransactionBinding
import com.meta.metatools.activitiesorfragments.confirm.CONFIRM_CONFIG_MODEL
import com.meta.metatools.activitiesorfragments.confirm.ConfirmAdapter
import com.meta.metatools.models.ConfirmConfigModel
import com.meta.metatools.models.DocumentCameraType
import com.theartofdev.edmodo.cropper.CropImage


private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
private const val REQUEST_CODE_PERMISSIONS = 1109

private const val TAKE_PICTURE = 1301
private lateinit var filePath: String
private var backCamera: Boolean = false

class CameraActivity : AppCompatActivity() {

    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var viewFinder: TextureView
    private lateinit var buttonInfo: View
    private var confirmConfigModel: ConfirmConfigModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meta_camera)

        viewFinder = findViewById(R.id.view_finder)!!
        buttonInfo = findViewById(R.id.info_button)!!

        val bundle = intent.extras


        if (bundle != null) {
            confirmConfigModel = bundle.getParcelable(CONFIRM_CONFIG_MODEL) as? ConfirmConfigModel
        }

        buttonInfo.setOnClickListener {
            showInfoPhoto(confirmConfigModel ?: ConfirmConfigModel(),this@CameraActivity)
        }

        if (allPermissionsGranted()) {
            viewFinder.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
    }

    private fun startCamera() {

        val wm = getSystemService(WINDOW_SERVICE) as WindowManager?
        val displayMetrics = DisplayMetrics()
        wm!!.defaultDisplay.getMetrics(displayMetrics)

        val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
        val screenSize = Size(metrics.widthPixels, metrics.heightPixels)
        val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)


        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(screenSize)
            setTargetRotation(viewFinder.display.rotation)
            backCamera = intent?.getBooleanExtra(BACK_CAMERA, false) ?: false

            if (backCamera) {
                setLensFacing(CameraX.LensFacing.BACK)
                findViewById<View>(R.id.cameraMask).visibility = View.GONE
            } else setLensFacing(CameraX.LensFacing.FRONT)
        }.build()


        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {

            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)

            viewFinder.setSurfaceTexture(it.surfaceTexture)
            updateTransform()
        }

        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setCaptureMode(CaptureMode.MIN_LATENCY)
                setTargetAspectRatio(screenAspectRatio)
                if (backCamera) setLensFacing(CameraX.LensFacing.BACK)
                else setLensFacing(CameraX.LensFacing.FRONT)

            }.build()

        val imageCapture = ImageCapture(imageCaptureConfig)

        findViewById<ImageView>(R.id.capture_button)?.setOnClickListener {
            val file = File(
                externalMediaDirs?.first(),
                "${System.currentTimeMillis()}.jpg"
            )

            imageCapture.takePicture(file, executor,
                object : OnImageSavedListener {
                    override fun onError(
                        imageCaptureError: ImageCaptureError,
                        message: String,
                        exc: Throwable?
                    ) {
                        viewFinder.post {
                            Toast.makeText(
                                this@CameraActivity,
                                "Error al intentar tomar la prueba, por favor verifica que cuentes almacenamiento disponible",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onImageSaved(file: File) {
                        CropImage.activity(file.toUri())
                            .start(this@CameraActivity)
                    }
                })
        }

        CameraX.bindToLifecycle(this, preview, imageCapture)
    }

    private fun updateTransform() {
        val matrix = Matrix()

        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        val rotationDegrees = when (viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)
        viewFinder.setTransform(matrix)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                viewFinder.post { startCamera() }
            } else {
                Toast.makeText(
                    this,
                    "Permisos no otorgados.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val resultUri = result.uri
                filePath = resultUri.path.orEmpty()
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra("filePath", filePath)
                })
                finish()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.getError();
            }
        }
    }

    fun showInfoPhoto(model: ConfirmConfigModel, context: Context){
        var confirmAdapter = ConfirmAdapter()
        val dialog = Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        val binding: FragmentConfirmTransactionBinding = FragmentConfirmTransactionBinding.inflate(
            LayoutInflater.from(context)
        )
        binding.let { dialog.setContentView(it.root) }
        dialog.setCancelable(false)


            model.headerText?.let {
                binding.headerLabel.text = it
            }
            model.headerImageIcon?.let {
                binding.headerImage.setImageDrawable(
                    ContextCompat.getDrawable(context, it
                    )
                )
            }
            model.listConfirmModel?.let {
                confirmAdapter?.addItems(it)
            }
            model.filePathImage?.let { filePath ->
                Glide.with(context).load(filePath).into(binding.headerImage)
            }
        binding.confirmList.apply {
                adapter = confirmAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        binding.rightButton.text = "Entendido"
        binding.rightButton.setOnClickListener {
                dialog?.dismiss()
            }

        binding.confirmButton.visibility = View.GONE
        binding.chkNoShow.visibility = View.GONE
        binding.leftButton.visibility = View.GONE


        dialog?.show()
    }
}