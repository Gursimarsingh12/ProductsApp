package com.chatwise.productsapp.products.presentation.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chatwise.productsapp.R
import com.chatwise.productsapp.core.utils.Resource
import com.chatwise.productsapp.databinding.ActivityMainBinding
import com.chatwise.productsapp.products.presentation.adapter.ProductItemAdapter
import com.chatwise.productsapp.products.presentation.viewmodels.ProductsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: ProductsViewModel by viewModels<ProductsViewModel>()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getProducts(viewModel)
    }

    private fun getProducts(viewModel: ProductsViewModel){
        viewModel.productsState.observe(this){ state ->
            when(state){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    val productItemAdapter = ProductItemAdapter(state.data ?: emptyList(), this) { productId ->
                        startActivity(Intent(this, ProductDetailScreenActivity::class.java).apply {
                            putExtra("productId", productId)
                        })
                    }
                    binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.recyclerView.adapter = productItemAdapter
                }
                is Resource.Error -> {
                    Snackbar.make(binding.root, state.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        }
    }

    private fun hideLoading(){
        binding.progressBar.hide()
    }

    private fun showLoading() {
        binding.progressBar.show()
    }

}