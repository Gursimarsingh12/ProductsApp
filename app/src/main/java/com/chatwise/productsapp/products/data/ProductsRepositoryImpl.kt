package com.chatwise.productsapp.products.data

import android.util.Log
import com.chatwise.productsapp.api.models.Product
import com.chatwise.productsapp.api.models.ProductsList
import com.chatwise.productsapp.api.service.ProductsApiService
import com.chatwise.productsapp.core.utils.Resource
import com.chatwise.productsapp.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ProductsApiService
): ProductsRepository {
    override suspend fun getProducts(): Flow<Resource<List<Product>>> = flow{
        emit(Resource.Loading)
        try {
            val response = apiService.getProducts()
            if(response.isSuccessful){
                Log.d("response", response.body().toString())
                emit(Resource.Success(response.body()?.products))
            }else{
                emit(Resource.Error(response.errorBody().toString()))
            }
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}