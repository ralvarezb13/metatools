package com.meta.metatools

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.text.format.DateFormat
import android.util.Base64
import android.util.Base64OutputStream
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.meta.metatools.strings.removeAccentMark
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class MetaTools {

        enum class MFGReplacementPaymentType {
            TYPE_REFERENCE,
            TYPE_PAYNET
        }

        enum class MFGReplacementStatus(val type:Int){
            PENDING(1),
            PAID(2),
            EXPIRED(3)
        }

        enum class MFGCardReplacementType(val replacementId: Int) {
            COMMERCE(1),
            BANK(2)
        }

        enum class MFGLevelUpStatusType(val id: Int) {
            INITIAL(0),
            PENDING(1),
            ERROR(2),
            VALIDATED(3)
        }

        enum class MFGLevelUpErrorType(val id: Int) {
            SIGNATURE_ERROR(0),
            DOCUMENT_ERROR(1)
        }
        enum class MFGFilteringType {
            WEEKLY {
                override val filterName: String
                    get() = "Semanal"
            },
            MONTHLY {
                override val filterName: String
                    get() = "Mensual"
            },
            QUARTERLY {
                override val filterName: String
                    get() = "Trimestral"
            };

            abstract val filterName: String
        }

        enum class MFGAddressType(var componentType: String) {
            STREET("route"),
            OUTDOOR_NUMBER("street_number"),
            ZIP_CODE("postal_code"),
            NEIGHBORHOOD("sublocality_level_1"),
            COUNTY("locality"),
            STATE("administrative_area_level_1"),
            COUNTRY("country")
        }

    

        enum class MFGNotificationType(val notificationCode: Int) {
            TRANSACTION(1),
            SERVICES(2)
        }

        enum class MFGCardStatusType {
            ACTIVE_STATUS {
                override val cardStatus: String
                    get() = "Active"
            },
            FROZEN_STATUS {
                override val cardStatus: String
                    get() = "Freezed"
            },
            BLOCKED_STATUS {
                override val cardStatus: String
                    get() = "Blocked"
            };

            abstract val cardStatus: String
        }

        enum class MFGCartType {
            DIGITAL_CARD_TYPE {
                override val cardType: Int
                    get() = 0
            },
            PHYSICAL_CARD_TYPE {
                override val cardType: Int
                    get() = 1
            };

            abstract val cardType: Int
        }

        enum class MFGDocumentType(val documentId: Int, val documentName: String) {
            INE(0, "INE"),
            PASSPORT(1, "Pasaporte")
        }

        enum class MFGDisclaimerType(
            val disclaimerId: Int,
            val disclaimerName: String,
            val disclaimerDirectory: String
        ) {
            TCS_CACAO(1, "Términos y condiciones de uso CACAO", "Documentos"),
            PRIVACY_CACAO(2, "Aviso de privacidad CACAO", "Documentos"),
            CONTRACT_CACAO(3, "Contrato de emisión y deposito CACAO", "Documentos")
        }

        enum class MFGUserType {
            GROUP_USER {
                override val userTypeCode: Int
                    get() = 2
            },
            DEBIT_USER {
                override val userTypeCode: Int
                    get() = 3
            };

            abstract val userTypeCode: Int
        }

        enum class MFGTicketModelType {
            RECYCLER_ITEM_TYPE,
            DESTINATION_TYPE
        }

        val getCurrentDateForTicket: String
            get() {
                return SimpleDateFormat(
                    "dd MMMM yyyy HH:mm:ss",
                    Locale.forLanguageTag("es")
                ).format(System.currentTimeMillis())
            }

        val randomValues = List(1) {
            Random.nextInt(0, 100)
        }

        fun getTimestampId(): String {
            val date = Date()
            val timestamp = DateFormat.format("_yyyy-MM-dd_HH:mm:ss_", date).toString()
            return timestamp.plus(randomValues.first())
        }

        val curpGendersList: List<Pair<String, String>>
            get() = listOf(
                Pair("Masculino", "H"),
                Pair("Femenino", "M")
            )


        val curpStatesList: List<Pair<String, String>>
            get() = listOf(
                Pair("Aguascalientes", "AS"),
                Pair("Baja California", "BC"),
                Pair("Baja California Sur", "BS"),
                Pair("Campeche", "CC"),
                Pair("Chiapas", "CS"),
                Pair("Chihuahua", "CH"),
                Pair("Ciudad de México", "DF"),
                Pair("Coahuila", "CL"),
                Pair("Colima", "CM"),
                Pair("Durango", "DG"),
                Pair("Guanajuato", "GT"),
                Pair("Guerrero", "GR"),
                Pair("Hidalgo", "HG"),
                Pair("Jalisco", "JC"),
                Pair("México", "MC"),
                Pair("Michoacán", "MN"),
                Pair("Morelos", "MS"),
                Pair("Nayarit", "NT"),
                Pair("Nuevo León", "NL"),
                Pair("Oaxaca", "OC"),
                Pair("Puebla", "PL"),
                Pair("Querétaro", "QO"),
                Pair("Quintana Roo", "QR"),
                Pair("San Luis Potosí", "SP"),
                Pair("Sinaloa", "SL"),
                Pair("Sonora", "SR"),
                Pair("Tabasco", "TC"),
                Pair("Tamaulipas", "TS"),
                Pair("Tlaxcala", "TL"),
                Pair("Veracruz", "VZ"),
                Pair("Yucatán", "YN"),
                Pair("Zacatecas", "ZS")
            )



    fun createRFC(lastName: String,lastNameM: String,name: String,date: String): String{
        val dates: List<String> = date.split("/")
        return (lastName.substring(0,2) +
                lastNameM.substring(0,1) +
                name.substring(0,1) +
                dates[2].substring(2,4) +
                dates[0] +
                dates[1]).uppercase().removeAccentMark()
    }

}

fun File.convertImageFileToBase64(): String {
    return ByteArrayOutputStream().use { outputStream ->
        Base64OutputStream(outputStream, Base64.NO_CLOSE).use { base64FilterStream ->
            this.inputStream().use { inputStream ->
                inputStream.copyTo(base64FilterStream)
            }
        }
        return@use outputStream.toString()
    }
}

fun Bitmap.convertBitmapToBase64(context: Context): String? {
    return try {
        val outputStream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
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