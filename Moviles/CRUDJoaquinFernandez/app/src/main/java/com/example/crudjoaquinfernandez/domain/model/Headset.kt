package com.example.crudjoaquinfernandez.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Headset(
    val id: Int,
    var name: String,
    var mic: Boolean,
    var bluetooth: Boolean,
) : Parcelable