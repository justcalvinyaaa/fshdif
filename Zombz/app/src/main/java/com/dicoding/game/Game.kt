package com.dicoding.game

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


@Parcelize
data class Game(
    val name: String?,
    val description: String?,
    val photo: Int?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )
    companion object : Parceler<Game> {

        override fun Game.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(description)
            photo?.let { parcel.writeInt(it) }
        }
        override fun create(parcel: Parcel): Game {
            return Game(parcel)
        }
    }
}