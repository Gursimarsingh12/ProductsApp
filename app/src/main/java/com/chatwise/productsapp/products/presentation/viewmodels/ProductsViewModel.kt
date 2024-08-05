package com.chatwise.productsapp.products.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatwise.productsapp.api.models.Product
import com.chatwise.productsapp.core.utils.Resource
import com.chatwise.productsapp.products.domain.use_case.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    private val _productsState = MutableLiveData<Resource<List<Product>>>(Resource.Idle)
    val productsState: LiveData<Resource<List<Product>>> = _productsState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productsUseCase.getProducts().collect { state ->
                _productsState.postValue(state)
            }
        }
    }
}
