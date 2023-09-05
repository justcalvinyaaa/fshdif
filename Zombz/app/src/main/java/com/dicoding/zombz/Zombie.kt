package com.dicoding.zombz

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Parcelize
data class Zombie(
    val name: String,
    val description: String,
    val photo: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    companion object : Parceler<Zombie> {
        override fun Zombie.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeInt(photo ?: 0)
        }

        override fun create(parcel: Parcel): Zombie {
            return Zombie(parcel)
        }
    }
}
