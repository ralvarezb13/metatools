package com.meta.metatools.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProgressModel(
    val title: String? = "Un momento… Estamos procesando la información",
    val image: Int? = null
) : Parcelable