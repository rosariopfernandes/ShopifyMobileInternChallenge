package com.myshopify.shopicruit.shopifyinternchallenge.productslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.myshopify.shopicruit.shopifyinternchallenge.R
import com.myshopify.shopicruit.shopifyinternchallenge.tagslist.TagsListActivity
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.content_simple_list.*

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val productTag = intent.extras.getString(TagsListActivity.PRODUCT_TAG_KEY)
        val viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        viewModel.getProducts(productTag).observe(this, Observer { products ->
            products?.let {
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = ProductsAdapter(it)
                recyclerView.visibility = View.VISIBLE
                ivEmptyState.visibility = View.GONE
            }
        })

    }

}
