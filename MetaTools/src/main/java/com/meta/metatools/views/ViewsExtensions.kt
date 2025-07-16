package com.meta.metatools.views

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.text.method.DigitsKeyListener
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import java.io.ByteArrayOutputStream
import android.util.Base64
import android.widget.EditText
import android.widget.Toast
import com.meta.metacomponents.input.MetaInputLayout
import com.meta.metatools.strings.ValidateNoEmail
import java.io.FileOutputStream

@Deprecated("Use Fragment.hideKeyboard() instead")
fun View.hideKeyboardFrom(context: Context) {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun ScrollView.getBitmapFromScroll(): Bitmap {
    val bitmap = Bitmap.createBitmap(
        this.getChildAt(0).width * 2,
        this.getChildAt(0).height * 2,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    canvas.scale(2.0f, 2.0f)
    canvas.drawColor(Color.WHITE)

    this.getChildAt(0).draw(canvas)
    return bitmap
}

fun Bitmap.convertBitmapToBase64(context: Context): String? {
    return try {
        val outputStream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
        Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "Algo ocurrió al intentar leer el documento, por favor, inténtalo de nuevo.",
            Toast.LENGTH_LONG
        ).show()
        ""
    }
}

private fun ScrollView.writeFile(
    fileOutPutStream: FileOutputStream
): Boolean? {
    val bitmap = this.getBitmapFromScroll()
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val data = byteArrayOutputStream.toByteArray()

    return try {
        fileOutPutStream.write(data)
        fileOutPutStream.flush()
        fileOutPutStream.close()
        true
    } catch (e: Exception) {
        false
    }
}

fun EditText.keyListenerOnlyWords() {
    this.keyListener = DigitsKeyListener.getInstance("áéíóúäëïöüabcdefghijklmnopqrstuvwxyzÁÉÍÓÚÄËÏÖÜABCDEFGHIJKLMNOPQRSTUVWXYZ ")
}

fun EditText.keyListenerCURP() {
    this.keyListener = DigitsKeyListener.getInstance("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
}

fun EditText.keyListenerOnlyNumbers() {
    this.keyListener = DigitsKeyListener.getInstance("0123456789")
}

fun EditText.keyListenerEmail() {
    this.keyListener = DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.@")
}


fun MetaInputLayout.ValidateNames(): Boolean{
    val words = this.text.toString().split(" ")
    words.map {
        if(it.length > 37){
            this.error = "Las palabras pueden tener un max de 37 caracteres"
            return false
        }
    }
    this.error = null
    return true
}

fun MetaInputLayout.isEmail(): Boolean{
    if(this.isOptional){
        if(this.text?.isNotEmpty() == true){
            if(this.text.toString().ValidateNoEmail()){
                this.error = "El correo no es valido"
                return false
            }
        }
        this.error = null
        return true
    }
    if(!this.text.toString().ValidateNoEmail()){
        this.error = "El correo no es valido"
        return false
    }
    this.error = null
    return true
}