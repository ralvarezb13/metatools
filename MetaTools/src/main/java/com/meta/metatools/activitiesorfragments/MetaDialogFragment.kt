package com.meta.metatools.activitiesorfragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MetaDialogFragment(
    private val message: String = "",
    private val onSuccess: () -> Unit): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                .setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User positive the dialog
                        onSuccess()
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog

                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}