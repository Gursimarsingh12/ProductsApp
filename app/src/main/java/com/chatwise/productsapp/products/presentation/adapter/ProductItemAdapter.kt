package com.chatwise.productsapp.products.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chatwise.productsapp.api.models.Product
import com.chatwise.productsapp.databinding.RvItemProductBinding
import com.squareup.picasso.Picasso

class ProductItemAdapter(
    private val products: List<Product>,
    private val context: Context,
    private val onClick: (Int) -> Unit
): RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    inner class ProductItemViewHolder(var binding: RvItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = RvItemProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.binding.cardView.setOnClickListener {
            onClick(products[position].id ?: 1)
        }
        holder.binding.productName.text = products[position].title
        holder.binding.productDescription.text = products[position].description
        Picasso.get().load(products[position].thumbnail).into(holder.binding.productImage)
        anim(holder.itemView)
    }

    private fun anim(view: View){
        val animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 1500
        view.startAnimation(animation)
    }
}