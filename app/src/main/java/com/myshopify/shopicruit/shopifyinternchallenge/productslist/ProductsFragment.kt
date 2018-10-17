package com.myshopify.shopicruit.shopifyinternchallenge.productslist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myshopify.shopicruit.shopifyinternchallenge.R
import kotlinx.android.synthetic.main.content_simple_list.*


class ProductsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.content_simple_list, container, false)

        val productTag = ProductsFragmentArgs.fromBundle(arguments).productTag

        val viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        viewModel.getProducts(productTag).observe(this, Observer { products ->
            products?.let {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = ProductsAdapter(it)
                recyclerView.visibility = View.VISIBLE
                ivEmptyState.visibility = View.GONE
            }
        })

        return fragmentView
    }

}
