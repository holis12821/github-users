package com.example.githubuserapp.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    var username: String? = null,
    var name: String? = null,
    var avatar: Int? = null,
    var company: String? = null,
    var location: String? = null,
    var repository: String? = null,
    var follower: String? = null,
    var following: String? = null
): Parcelable
