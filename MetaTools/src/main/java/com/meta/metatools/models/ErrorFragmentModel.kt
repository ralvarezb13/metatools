package com.meta.metatools.models

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.meta.metatools.R
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorFragmentModel(
    val title: String? = "",
    val titleColor: Int? = R.color.Leon,
    val buttonTitleAction: String? = "",
    val image: Int? = null,
    val buttonTitleSecondAction: String? = ""
) : Parcelable {
    @IgnoredOnParcel
    var action: (Fragment) -> Unit = {}
    var secondAction: (Fragment) -> Unit = {}
}