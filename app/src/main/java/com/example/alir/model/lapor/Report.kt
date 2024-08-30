package com.example.alir.model.lapor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Report(
    val id : String = "",
    val reporterName: String = "",
    val date: String = "",
    val location: String = "",
    val photoUrl: String = "",
    val status:Int = 0
) : Parcelable
