package com.gresmer.farklescoreboard

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by kgresmer on 3/18/18.
 */


class RosterPlayer(
        val id: Long,
        var name: String,
        var wins: Int,
        var losses: Int,
        var bestScore: Int,
        var isOnRoster: Boolean) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(wins)
        parcel.writeInt(losses)
        parcel.writeInt(bestScore)
        parcel.writeByte(if (isOnRoster) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RosterPlayer> {
        override fun createFromParcel(parcel: Parcel): RosterPlayer {
            return RosterPlayer(parcel)
        }

        override fun newArray(size: Int): Array<RosterPlayer?> {
            return arrayOfNulls(size)
        }
    }

}
