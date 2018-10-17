package com.myshopify.shopicruit.shopifyinternchallenge.productslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myshopify.shopicruit.shopifyinternchallenge.api.Product
import com.myshopify.shopicruit.shopifyinternchallenge.api.ProductRepository
import com.shopify.livedataktx.map

class ProductsViewModel : ViewModel() {

    private var products: LiveData<ArrayList<Product>>

    init {
        products = ProductRepository.getInstance().getProducts()
    }

    fun getProducts(productTag: String): LiveData<ArrayList<Product>> {
        return products.map { productsList ->
            productsList?.let {
                val newList = ArrayList<Product>()
                it.forEach { product ->
                    if (product.tags.split(", ").contains(productTag)) {
                        newList.add(product)
                    }
                }
                return@map newList
            }
        }
    }

}