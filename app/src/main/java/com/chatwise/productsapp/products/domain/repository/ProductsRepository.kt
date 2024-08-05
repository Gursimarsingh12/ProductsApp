package com.chatwise.productsapp.products.domain.repository

import com.chatwise.productsapp.api.models.Product
import com.chatwise.productsapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository{
    suspend fun getProducts():  Flow<Resource<List<Product>>>
}