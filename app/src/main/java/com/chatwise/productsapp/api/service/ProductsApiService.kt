package com.chatwise.productsapp.api.service

import com.chatwise.productsapp.api.models.ProductsList
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApiService {
    @GET("/products")
    suspend fun getProducts(): Response<ProductsList>
}