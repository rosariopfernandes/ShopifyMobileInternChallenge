package com.myshopify.shopicruit.shopifyinternchallenge.api

data class Product(
    var id: Long ,
    var title: String,
    var body_html: String,
    var vendor: String,
    var product_type: String,
    var created_at: String,
    var handle: String, // TODO: Use this for dynamic linking
    var updated_at: String,
    var published_at: String,
    var tags: String,
    var variants: ArrayList<Product>,
    var images: ArrayList<Image>,
    var image: Image,
    var inventory_quantity: Int = 0
)