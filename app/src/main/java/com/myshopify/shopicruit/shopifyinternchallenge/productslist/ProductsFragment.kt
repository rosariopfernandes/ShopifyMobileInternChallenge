package com.myshopify.shopicruit.shopifyinternchallenge.productslist


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myshopify.shopicruit.shopifyinternchallenge.MainActivity
import com.myshopify.shopicruit.shopifyinternchallenge.R
import kotlinx.android.synthetic.main.content_simple_list.*


class ProductsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.content_simple_list, container, false)

        val productTag = arguments?.getString(MainActivity.PRODUCT_TAG_KEY)

        productTag?.let { tag ->
            val viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
            viewModel.getProducts(tag).observe(this, Observer { products ->
                products?.let {
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = ProductsAdapter(it)
                    recyclerView.visibility = View.VISIBLE
                    ivEmptyState.visibility = View.GONE
                }
            })
        }

        return fragmentView
    }

}
