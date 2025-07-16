package com.meta.metatools.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class DocumentCameraType {
    INEFront, INEBack, CURP, Voucher, Selfie, BusinessKey,
    PrivacyNotice, SignatureProspect,
    SignatureEmployee, UNKNOWN
}