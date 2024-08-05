package com.chatwise.productsapp.products.domain.use_case

import com.chatwise.productsapp.api.models.Product
import com.chatwise.productsapp.core.utils.Resource
import com.chatwise.productsapp.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend fun getProducts(): Flow<Resource<List<Product>>> = repository.getProducts()
}