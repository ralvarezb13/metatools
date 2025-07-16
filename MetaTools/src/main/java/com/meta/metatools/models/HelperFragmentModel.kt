package com.meta.metatools.models

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.meta.metatools.R
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HelperFragmentModel(
    val title: String? = "",
    val subtitle: String? = "",
    val buttonTitle: String? = "",
    val image: Int? = null,
    val titleColor: Int? = R.color.Bajio_80p
) : Parcelable {
    @IgnoredOnParcel
    var action: (Fragment) -> Unit = {}
}