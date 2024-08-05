package com.chatwise.productsapp.di

import com.chatwise.productsapp.api.service.ProductsApiService
import com.chatwise.productsapp.products.data.ProductsRepositoryImpl
import com.chatwise.productsapp.products.domain.repository.ProductsRepository
import com.chatwise.productsapp.products.domain.use_case.ProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    @Provides
    @Singleton
    fun providesOkhttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesProductsApiService(retrofit: Retrofit): ProductsApiService = retrofit.create(ProductsApiService::class.java)

    @Provides
    @Singleton
    fun providesProductsRepository(apiService: ProductsApiService): ProductsRepository = ProductsRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun providesProductsUseCase(repository: ProductsRepository): ProductsUseCase = ProductsUseCase(repository)
}