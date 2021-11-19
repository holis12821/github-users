package com.example.githubuserapp.data.response.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "table_user")
@Parcelize
data class ItemsItem(
                     @PrimaryKey(autoGenerate = true)
                     @SerializedName("id")
                     val id: Int? = null,

                     @ColumnInfo(name = "login")
                     @SerializedName("login")
                     val login: String? = null,

                     @ColumnInfo(name = "avatar_url")
                     @SerializedName("avatar_url")
                     val avatarUrl:  String? = null,

                     @ColumnInfo(name = "html_url")
                     @SerializedName("html_url")
                     val html_url: String? = null,

                     @ColumnInfo(name = "name")
                     @SerializedName("name")
                     val name: String? = null,

                     @ColumnInfo(name = "location")
                     @SerializedName("location")
                     val location: String? = null,

                     @ColumnInfo(name = "company")
                     @SerializedName("company")
                     val company: String? = null,

                     @ColumnInfo(name = "email")
                     @SerializedName("email")
                     val email: String? = null,

                     @ColumnInfo(name = "type")
                     @SerializedName("type")
                     val type: String? = null,

                     @ColumnInfo(name = "public_repos")
                     @SerializedName("public_repos")
                     val publicRepos: Int? = 0,

                     @ColumnInfo(name = "followers")
                     @SerializedName("followers")
                     val followers: Int? = 0,

                     @ColumnInfo(name = "following")
                     @SerializedName("following")
                     val following: Int? = 0,

                     @ColumnInfo(name = "isFavorite")
                     var isFavorite: Boolean = false
                    ) : Parcelable