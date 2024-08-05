package com.chatwise.productsapp.api.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("images")
    val photoUrl: List<String>? = null
)