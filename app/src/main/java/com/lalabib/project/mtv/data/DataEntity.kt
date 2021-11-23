package com.lalabib.project.mtv.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataEntity (
    var id: String?,
    var Title: String?,
    var overview: String?,
    var genre: String?,
    var rate: String?,
    var poster: String?,
    ):Parcelable