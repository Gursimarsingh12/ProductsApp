package com.chatwise.productsapp.api.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("images")
    val photoUrl: List<String>? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("rating")
    val rating: Float? = null,
    @SerializedName("price")
    val price: Float? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("discountPercentage")
    val discountPercentage: Float? = null
)