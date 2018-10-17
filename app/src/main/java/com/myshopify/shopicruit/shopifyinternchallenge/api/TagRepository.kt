package com.myshopify.shopicruit.shopifyinternchallenge.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TagRepository {
    private var productService: ProductService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(ProductRepository.SHOPIFY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        productService = retrofit.create(ProductService::class.java)
    }

    fun getTags(): LiveData<ArrayList<String>> {
        val tagsLiveData = MutableLiveData<ArrayList<String>>()
        var tagsSet  = setOf<String>()

        GlobalScope.launch(Dispatchers.Main) {
            val request = productService.getProducts(1, ProductRepository.ACCESS_TOKEN)
            val response = request.await()
            if (response.isSuccessful) {
                response.body()?.products?.let {
                    it.forEach { product ->
                        val tagsArray = product.tags.split(", ")
                        for (tag in tagsArray) {
                            tagsSet += tag
                        }
                    }
                    tagsLiveData.value = ArrayList(tagsSet.sorted())
                }
            } else {
                tagsLiveData.value = null
            }
        }
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