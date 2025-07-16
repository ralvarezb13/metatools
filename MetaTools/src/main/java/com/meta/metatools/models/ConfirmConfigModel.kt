package com.meta.metatools.models

import android.content.Intent
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmConfigModel(
    var headerImageIcon: Int? = null,
    var headerText: String? = null,
    var filePathImage: String? = null,
    var listConfirmModel: ArrayList<ConfirmModel>? = null,
    var leftVisibility: Boolean? = null,
    var leftButtonText: String? = null,
    var rightButtonText: String? = null,
    var rightVisibility: Boolean? = null,
    var confirmVisibility: Boolean? = null,
    var rigthText: String? = null,
    var checkVisibility: Boolean? = null,
    var skipAdvice: Boolean? = null,
    var reviewImage: Boolean? = null,
    var openBackCamera: Boolean? = true,
    var typeDocument: DocumentCameraType? = DocumentCameraType.UNKNOWN
) : Parcelable {
    @IgnoredOnParcel
    var actionCheck: ((Boolean) -> Unit)? = null

    @IgnoredOnParcel
    var actionRight: ((String) -> Unit)? = null

    @IgnoredOnParcel
    var actionLeft: (() -> Unit)? = null

    @IgnoredOnParcel
    var dataRequest: ((Intent?) -> Unit)? = null

    @IgnoredOnParcel
    var openInfo: (() -> Unit)? = null
}
