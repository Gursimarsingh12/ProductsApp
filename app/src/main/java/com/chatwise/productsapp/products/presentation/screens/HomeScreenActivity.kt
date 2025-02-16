package com.chatwise.productsapp.products.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chatwise.productsapp.R
import com.chatwise.productsapp.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.viewProductsButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}