package com.chatwise.productsapp.products.presentation.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chatwise.productsapp.R
import com.chatwise.productsapp.core.utils.Resource
import com.chatwise.productsapp.databinding.ActivityProductDetailScreenBinding
import com.chatwise.productsapp.products.presentation.viewmodels.ProductsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailScreenActivity : AppCompatActivity() {
    private val binding: ActivityProductDetailScreenBinding by lazy {
        ActivityProductDetailScreenBinding.inflate(layoutInflater)
    }
    private val viewModel: ProductsViewModel by viewModels<ProductsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productId = intent.getIntExtra("productId", 1)
        viewModel.productsState.observe(this) { state ->
            when (state) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    hideLoading()
                    val product = state.data?.filter { it.id == productId }?.map { it }?.get(0)
                    if (product != null) {
                        binding.productName.text = product.title
                        binding.productDescription.text = product.description
                        Picasso.get().load(product.photoUrl?.get(0)).into(binding.productImage)
                        binding.brandName.text = product.brand
                        binding.rating.text = product.rating.toString()
                        binding.price.text = product.price.toString()
                        binding.categoryName.text = product.category
                        binding.discountedPercentage.text = product.discountPercentage.toString()
                    } else {
                        Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }
    private fun hideLoading() {
        binding.progressBar.hide()
    }

    private fun showLoading() {
        binding.progressBar.show()
    }
}
