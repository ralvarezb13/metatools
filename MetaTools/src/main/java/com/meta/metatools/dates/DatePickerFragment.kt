package com.meta.metatools.dates

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFragment(val listener: (date: String) -> Unit) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(activity as Context, this, year, month, day)
        val timeStamp = SimpleDateFormat("dd/MM/yyyy").parse(createDateString((year-20),month,day))
        picker.datePicker.maxDate = timeStamp.time
        return picker
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(createDateString(year,month,dayOfMonth))
    }

    private fun createDateString(year: Int, month: Int, dayOfMonth: Int): String{
        var mes = (month + 1).toString()
        if (mes.toInt() < 10) {
            mes = "0$mes"
        }
        var dia = (dayOfMonth).toString()
        if (dia.toInt() < 10) {
            dia = "0$dia"
        }
        return "$dia/$mes/$year"
    }

}