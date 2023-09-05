package com.dicoding.zombz

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Zombie(
    val name : String,
    val description : String,
    val photo : Int
) : Parcelable
