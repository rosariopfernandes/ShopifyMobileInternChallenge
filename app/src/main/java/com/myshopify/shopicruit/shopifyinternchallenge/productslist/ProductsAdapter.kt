package com.myshopify.shopicruit.shopifyinternchallenge.productslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.myshopify.shopicruit.shopifyinternchallenge.R
import com.myshopify.shopicruit.shopifyinternchallenge.api.Product

class ProductsAdapter(
        var products: ArrayList<Product>?
) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ProductsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product,
                parent, false)
        return ProductsViewHolder(v)
    }

    override fun getItemCount() = products?.size ?: 0

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        products?.let {
            val product = it[position]
            holder.bindTo(product)
        }
    }

    inner class ProductsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvProductName: TextView
        var tvInventory: TextView
        var ivProduct: ImageView

        init {
            tvProductName = v.findViewById(R.id.tvProductName)
            tvInventory = v.findViewById(R.id.tvInventory)
            ivProduct = v.findViewById(R.id.ivProduct)
        }

        fun bindTo(product: Product) {
            tvProductName.text = product.title

            var totalInventory = 0
            for (variant in product.variants) {
                totalInventory += variant.inventory_quantity
            }
            tvInventory.text =
                    tvInventory.context.getString(R.string.inventory_available, totalInventory)


            Glide.with(ivProduct.context)
                    .load(product.image.src)
                    .into(ivProduct)
        }
    }

}