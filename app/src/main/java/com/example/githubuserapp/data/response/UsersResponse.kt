package com.example.githubuserapp.data.response

import com.google.gson.annotations.SerializedName
/**
 * This class used base response  to handle API.
 * @param T ued for generic parameter type where value list data
 * where the value of the data view is passing to the generic parameter type.*/
data class UsersResponse(@SerializedName("total_count")
                         val totalCount: Int = 0,
                         @SerializedName("incomplete_results")
                         val incompleteResults: Boolean = false,
                         @SerializedName("items")
                         val items: List<ItemsItem>? = null)