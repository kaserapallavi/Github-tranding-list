package com.example.motilaloswalassignment.data.remote.model

import android.os.Parcel
import android.os.Parcelable
import com.example.motilaloswalassignment.data.local.entity.GithubEntity
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


class GithubApiResponse(@SerializedName("total_count")
                        var totalCount: Long,

                        var items: ArrayList<GithubEntity>?
) : Parcelable {

    constructor(source: Parcel) : this(
            source.readLong(),
            source.createTypedArrayList(GithubEntity.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(totalCount)
        writeTypedList(items)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GithubApiResponse> = object : Parcelable.Creator<GithubApiResponse> {
            override fun createFromParcel(source: Parcel): GithubApiResponse = GithubApiResponse(source)
            override fun newArray(size: Int): Array<GithubApiResponse?> = arrayOfNulls(size)
        }
    }
}
