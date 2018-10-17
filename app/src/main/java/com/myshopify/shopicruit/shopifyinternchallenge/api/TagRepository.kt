package com.myshopify.shopicruit.shopifyinternchallenge.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TagRepository {
    private var productService: ProductService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(ProductRepository.SHOPIFY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        productService = retrofit.create(ProductService::class.java)
    }

    fun getTags(): LiveData<ArrayList<String>> {
        val tagsLiveData = MutableLiveData<ArrayList<String>>()
        var tagsSet  = setOf<String>()
        productService.getProducts(1, ProductRepository.ACCESS_TOKEN)
                .enqueue(object: Callback<ProductsList> {

                    override fun onResponse(call: Call<ProductsList>?,
                                            response: Response<ProductsList>) {
                        response.body()?.products?.let {
                            it.forEach { product ->
                                val tagsArray = product.tags.split(", ")
                                for (tag in tagsArray) {
                                    tagsSet += tag
                                }
                            }
                            tagsLiveData.value = ArrayList(tagsSet.sorted())
                        }
                    }

                    override fun onFailure(call: Call<ProductsList>, t: Throwable) {
                        tagsLiveData.value = null
                        t.printStackTrace()
                    }
                })
        return tagsLiveData
    }

    companion object {

        private var instance: TagRepository? = null

        fun getInstance(): TagRepository =
                instance ?: synchronized(this) {
                    instance ?: TagRepository().also { instance = it }
                }

    }
}