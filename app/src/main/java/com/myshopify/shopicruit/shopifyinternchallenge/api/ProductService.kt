package com.myshopify.shopicruit.shopifyinternchallenge.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("admin/products.json")
    fun getProducts(
        @Query("page") page: Int,
        @Query("access_token") accessToken: String
    ): Call<ProductsList>

    @GET("admin/products/{id}.json")
    fun getProduct(
        @Path("id") productId: Int,
        @Query("access_token") accessToken: String
    ): Call<Product>

}