package com.myshopify.shopicruit.shopifyinternchallenge.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository {
    private var productService: ProductService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(SHOPIFY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        productService = retrofit.create(ProductService::class.java)
    }

    fun getProduct(productId: Int): LiveData<Product> {
        val data = MutableLiveData<Product>()
        productService.getProduct(productId, ACCESS_TOKEN)
                .enqueue(object: Callback<Product> {

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                data.value = null
                t.printStackTrace()
            }
        })
        return data
    }

    fun getProducts(): LiveData<ArrayList<Product>> {
        val data = MutableLiveData<ArrayList<Product>>()
        productService.getProducts(1, ACCESS_TOKEN)
                .enqueue(object: Callback<ProductsList> {

                    override fun onResponse(call: Call<ProductsList>,
                                            response: Response<ProductsList>) {
                        data.value = response.body()?.products
                    }

                    override fun onFailure(call: Call<ProductsList>?, t: Throwable) {
                        data.value = null
                        t.printStackTrace()
                    }
                })
        return data
    }

    companion object {

        const val ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6"
        const val SHOPIFY_BASE_URL = "https://shopicruit.myshopify.com/"

        private var instance: ProductRepository? = null

        fun getInstance(): ProductRepository =
            instance ?: synchronized(this) {
                instance ?: ProductRepository().also { instance = it }
            }

    }
}
