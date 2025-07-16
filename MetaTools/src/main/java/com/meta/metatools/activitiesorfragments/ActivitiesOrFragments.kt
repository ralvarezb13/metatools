package com.meta.metatools.activitiesorfragments

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.meta.metacomponents.button.MetaButton
import com.meta.metacomponents.databinding.FragmentMetaSuccessBinding
import com.meta.metatools.base.BaseAdapter
import com.meta.metatools.strings.removeAccentMark
import android.view.LayoutInflater

import androidx.databinding.DataBindingUtil
import coil.load
import com.meta.metatools.models.SuccessFragmentModel


/*
fun Fragment.hideToolbar() {
    (activity as AppCompatActivity?)?.supportActionBar?.hide()
}

fun Fragment.showToolbar() {
    (activity as AppCompatActivity?)?.supportActionBar?.show()
}*/

fun Activity.showMetaDialog(
    message: String,
    title: String? = "Atención",
    positiveText: String? = "Cerrar",
    negativeText: String? = "Cancelar",
    negativeAction: (() -> Unit)? = {},
    positiveAction: (() -> Unit)? = {},
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveText) { dialogInterface, _ ->
            dialogInterface.dismiss()
            positiveAction?.invoke()
        }
        .setNegativeButton(negativeText) { dialogInterface, _ ->
            dialogInterface.dismiss()
            negativeAction?.invoke()
        }
        .setCancelable(false)
        .create()
        .show()
}

fun Fragment.hideFragment() = findNavController().navigateUp()

fun Fragment.toast(message: String) = Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()

fun Fragment.toastLong(message: String) = Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()

fun SearchView.addSearchFunction(
    dataList: List<Any>,
    adapter: BaseAdapter<Any, ViewDataBinding>?,
    predicate: (String, Any) -> Boolean
) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(query: String?): Boolean {
            val searchString = query?.uppercase().orEmpty()
            if (searchString.isEmpty())
                adapter?.addItems(dataList)
            else
                adapter?.addItems(dataList.filter {
                    predicate.invoke(searchString.removeAccentMark(), it)
                })
            return true
        }
    })
}


fun Fragment.showSuccess(model: SuccessFragmentModel){
    val dialog = Dialog(requireActivity(), R.style.Theme_Black_NoTitleBar_Fullscreen)
    val binding: FragmentMetaSuccessBinding = FragmentMetaSuccessBinding.inflate(
        LayoutInflater.from(context)
    )
    binding?.let { dialog.setContentView(it.root) }
    dialog?.setCancelable(false)
    binding.understoodButton.text = model.buttonTitle
    binding.titleSucessTextView.text = model.title
    model.image?.let {
        binding.imageSuccess.visibility = View.VISIBLE
        binding.containerDataSimple.root.visibility = View.GONE
        binding.containerView.background = null
        binding.imageSuccess.load(it)
    }
    binding.understoodButton.setOnClickListener {
        dialog?.dismiss()
        model.action.invoke(this)
    }
    if (model.buttonLeftTitle?.isNotEmpty() == true){
        binding.leftButton.visibility = View.VISIBLE
        binding.leftButton.text = model.buttonLeftTitle
        binding.leftButton.setOnClickListener {
            dialog?.dismiss()
            model.actionLeft.invoke(this)
        }
    }else {
        binding.leftButton.visibility = View.GONE
    }

    dialog?.show()

}

fun Fragment.showSuccessDismiss(model: SuccessFragmentModel){
    val dialog = Dialog(requireActivity(), R.style.Theme_Black_NoTitleBar_Fullscreen)
    val binding: FragmentMetaSuccessBinding = FragmentMetaSuccessBinding.inflate(
        LayoutInflater.from(context)
    )
    binding?.let { dialog.setContentView(it.root) }
    dialog?.setCancelable(false)
    binding.understoodButton.text = model.buttonTitle
    binding.titleSucessTextView.text = model.title
    model.image?.let {
        binding.imageSuccess.visibility = View.VISIBLE
        binding.containerDataSimple.root.visibility = View.GONE
        binding.containerView.background = null
        binding.imageSuccess.load(it)
    }
    binding.understoodButton.setOnClickListener {
        dialog?.dismiss()
    }
    if (model.buttonLeftTitle?.isNotEmpty() == true){
        binding.leftButton.visibility = View.VISIBLE
        binding.leftButton.text = model.buttonLeftTitle
        binding.leftButton.setOnClickListener {
            dialog?.dismiss()
        }
    }else {
        binding.leftButton.visibility = View.GONE
    }

    dialog?.show()

}



fun Fragment.hideKeyboard() {
    val imm: InputMethodManager =
        requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(requireView().windowToken, 0)
}
