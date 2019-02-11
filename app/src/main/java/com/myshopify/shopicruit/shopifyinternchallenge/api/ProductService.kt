package com.myshopify.shopicruit.shopifyinternchallenge.api

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("admin/products.json")
    fun getProducts(
        @Query("page") page: Int,
        @Query("access_token") accessToken: String
    ): Deferred<Response<ProductsList>>

    @GET("admin/products/{id}.json")
    fun getProduct(
        @Path("id") productId: Int,
        @Query("access_token") accessToken: String
    ): Call<Product>

}