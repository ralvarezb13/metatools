package com.meta.metatools.strings

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.meta.metatools.MetaTools
import java.util.*
import java.text.SimpleDateFormat

fun String.ValidateNoEmail(): Boolean{
    if (this.isEmpty() || !this.matches(Regex("^[^@]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}\$"))) {
        return true
    }
    return false
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it ->
    it.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

fun String.removeAccentMark(): String {
    return this.replace("Á","A")
            .replace("É","E")
            .replace("Í","I")
            .replace("Ó","O")
            .replace("Ú","U")
            .replace("á","a")
            .replace("é","e")
            .replace("í","i")
            .replace("ó","o")
            .replace("ú","u")
}

fun String.validateWithRegex(regex: String): Boolean{
    if (this.isNotEmpty() && this.matches(Regex(regex))) {
        return true
    }
    return false
}

fun String.fetchVerificationCode(): String {
    return Regex("(\\d{6})").find(this.replace(" ", ""))?.value ?: ""
}

fun String.getInitials(): String {
    return if (this.isNotEmpty()) {
        val splited = this.split(" ")
        when (splited.size) {
            1 -> splited.getOrNull(0)?.let {
                (it.getOrNull(0) ?: "" + it.getOrNull(1)).toString()
                    .uppercase(Locale.ROOT)
            } ?: "MF"
            else -> splited.getOrNull(0)?.let {
                (it.getOrNull(0) ?: "M").toString().uppercase(Locale.ROOT)
            } + splited.getOrNull(1)?.let {
                (it.getOrNull(0) ?: "F").toString().uppercase(Locale.ROOT)
            }
        }
    } else {
        this
    }
}

fun String.getBankName(): String {
    val bankList = arrayListOf<Pair<String, String>>()

    bankList.add(Pair("002", "BANAMEX"))
    bankList.add(Pair("006", "BANCOMEXT"))
    bankList.add(Pair("009", "BANOBRAS"))
    bankList.add(Pair("012", "BBVA BANCOMER"))
    bankList.add(Pair("014", "SANTANDER"))
    bankList.add(Pair("019", "BANJERCITO"))
    bankList.add(Pair("021", "HSBC"))
    bankList.add(Pair("022", "GE MONEY"))
    bankList.add(Pair("030", "BAJÍO"))
    bankList.add(Pair("032", "IXE"))
    bankList.add(Pair("036", "INBURSA"))
    bankList.add(Pair("037", "INTERACCIONES"))
    bankList.add(Pair("042", "MIFEL"))
    bankList.add(Pair("044", "SCOTIABANK"))
    bankList.add(Pair("058", "BANREGIO"))
    bankList.add(Pair("059", "INVEX"))
    bankList.add(Pair("060", "BANSI"))
    bankList.add(Pair("062", "AFIRME"))
    bankList.add(Pair("072", "BANORTE"))
    bankList.add(Pair("102", "THE ROYAL BANK"))
    bankList.add(Pair("103", "AMERICAN EXPRESS"))
    bankList.add(Pair("106", "BAMSA"))
    bankList.add(Pair("108", "TOKYO"))
    bankList.add(Pair("110", "JP MORGAN"))
    bankList.add(Pair("112", "BMONEX"))
    bankList.add(Pair("113", "VE POR MAS"))
    bankList.add(Pair("116", "ING"))
    bankList.add(Pair("124", "DEUTSCHE"))
    bankList.add(Pair("126", "CREDIT SUISSE"))
    bankList.add(Pair("127", "AZTECA"))
    bankList.add(Pair("128", "AUTOFIN"))
    bankList.add(Pair("129", "BARCLAYS"))
    bankList.add(Pair("130", "COMPARTAMOS"))
    bankList.add(Pair("131", "FAMSA"))
    bankList.add(Pair("132", "BMULTIVA"))
    bankList.add(Pair("133", "ACTINVER"))
    bankList.add(Pair("134", "WAL-MART"))
    bankList.add(Pair("135", "NAFIN"))
    bankList.add(Pair("136", "INTERBANCO"))
    bankList.add(Pair("137", "BANCOPPEL"))
    bankList.add(Pair("138", "ABC CAPITAL"))
    bankList.add(Pair("139", "UBS BANK"))
    bankList.add(Pair("140", "CONSUBANCO"))
    bankList.add(Pair("141", "VOLKSWAGEN"))
    bankList.add(Pair("143", "CIBanco"))
    bankList.add(Pair("145", "BBASE"))
    bankList.add(Pair("147", "BANKAOOL"))
    bankList.add(Pair("148", "PagaTodo"))
    bankList.add(Pair("150", "BIM"))
    bankList.add(Pair("155", "ICBC"))
    bankList.add(Pair("156", "SABADELL"))
    bankList.add(Pair("166", "BANSEFI"))
    bankList.add(Pair("168", "HIPOTECARIA FEDERAL"))
    bankList.add(Pair("600", "MONEXCB"))
    bankList.add(Pair("601", "GBM"))
    bankList.add(Pair("602", "MASARI CB"))
    bankList.add(Pair("604", "C.B. INBURSA"))
    bankList.add(Pair("605", "VALUÉ"))
    bankList.add(Pair("606", "ESTRUCTURADORES"))
    bankList.add(Pair("607", "TIBER"))
    bankList.add(Pair("608", "VECTOR"))
    bankList.add(Pair("610", "B&B"))
    bankList.add(Pair("611", "INTERCAM"))
    bankList.add(Pair("613", "MULTIVA"))
    bankList.add(Pair("614", "ACCIVAL"))
    bankList.add(Pair("615", "MERRILL LYNCH"))
    bankList.add(Pair("616", "FINAMEX"))
    bankList.add(Pair("617", "VALMEX"))
    bankList.add(Pair("618", "ÚNICA"))
    bankList.add(Pair("619", "MAPFRE"))
    bankList.add(Pair("620", "PROFUTURO"))
    bankList.add(Pair("621", "CB ACTINBER"))
    bankList.add(Pair("622", "OACTIN"))
    bankList.add(Pair("623", "SKANDIA"))
    bankList.add(Pair("624", "CONSULTORÍA"))
    bankList.add(Pair("626", "CBDEUTSCHE"))
    bankList.add(Pair("627", "ZURICH"))
    bankList.add(Pair("628", "ZURICHVI"))
    bankList.add(Pair("629", "SU CASITA"))
    bankList.add(Pair("630", "C.B. INTERCAM"))
    bankList.add(Pair("631", "C.I. BOLSA"))
    bankList.add(Pair("632", "BULLTICK C.B."))
    bankList.add(Pair("633", "STERLING"))
    bankList.add(Pair("634", "FINCOMUN"))
    bankList.add(Pair("636", "HDI SEGUROS"))
    bankList.add(Pair("637", "ORDER"))
    bankList.add(Pair("638", "AKALA"))
    bankList.add(Pair("640", "C.B. JP MORGAN"))
    bankList.add(Pair("642", "REFORMA"))
    bankList.add(Pair("646", "STP"))
    bankList.add(Pair("647", "TELECOMM"))
    bankList.add(Pair("648", "EVERCORE"))
    bankList.add(Pair("649", "SKANDIA"))
    bankList.add(Pair("651", "SEGMTY"))
    bankList.add(Pair("652", "ASEA"))
    bankList.add(Pair("653", "KUSPIT"))
    bankList.add(Pair("655", "SOFIEXPRESS"))
    bankList.add(Pair("656", "UNAGRA"))
    bankList.add(Pair("659", "OPCIONES EMPRESARIALES DEL NOROESTE"))
    bankList.add(Pair("670", "LIBERTAD"))
    bankList.add(Pair("674", "AXA"))
    bankList.add(Pair("677", "CAJA POP MEXICA"))
    bankList.add(Pair("679", "FND"))
    bankList.add(Pair("684", "TRANSFER"))
    bankList.add(Pair("901", "CLS"))
    bankList.add(Pair("902", "INDEVAL"))
    return bankList.find {
        it.first == this
    }?.second ?: "OTROS"
}

fun String.simpleDateToTextDate(startFormat: String, endFormat: String): String {
    val originPattern = SimpleDateFormat(startFormat, Locale.getDefault())
    val dateObj = originPattern.parse(this)
    val formatedPattern = SimpleDateFormat(endFormat, Locale.getDefault())
    return formatedPattern.format(dateObj!!)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}

fun String.stringDateToCalendar(): Calendar {
    val cal: Calendar = Calendar.getInstance(Locale.forLanguageTag("es"))
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.forLanguageTag("es"))
    cal.time = sdf.parse(this) ?: Date()
    return cal
}

fun String.downloadPdf(
    context: Context,
    fileName: String? = "Recibo_Meta",
    description: String? = "Descargando recibo para depósito"
) {
    val uri = Uri.parse(this)
    val downloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
    val request = DownloadManager.Request(uri)
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
    request.setTitle(fileName + MetaTools().getTimestampId() + ".pdf")
    request.setDescription(description)
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Meta")
    request.setMimeType("application/pdf")
    downloadManager?.enqueue(request)
}
