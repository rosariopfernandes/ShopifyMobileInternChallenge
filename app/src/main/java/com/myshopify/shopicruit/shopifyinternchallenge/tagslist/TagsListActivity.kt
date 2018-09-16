package com.myshopify.shopicruit.shopifyinternchallenge.tagslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.myshopify.shopicruit.shopifyinternchallenge.R
import com.myshopify.shopicruit.shopifyinternchallenge.productslist.ProductsActivity
import kotlinx.android.synthetic.main.activity_tags_list.*
import kotlinx.android.synthetic.main.content_simple_list.*
import mz.co.kidzkare.vaccines.util.RecyclerItemClickListener

class TagsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags_list)
        setSupportActionBar(toolbar)

        val viewModel = ViewModelProviders.of(this).get(TagsViewModel::class.java)
        viewModel.getTags().observe(this, Observer { tags ->
            tags?.let {
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = TagsAdapter(it)
                recyclerView.visibility = View.VISIBLE
                ivEmptyState.visibility = View.GONE

                recyclerView.addOnItemTouchListener(
                        RecyclerItemClickListener(
                                recyclerView,
                                object : RecyclerItemClickListener.OnItemClickListener {
                                    override fun onItemClick(view: View, position: Int) {
                                        val selectedTag = it[position]
                                        val intent = Intent(this@TagsListActivity,
                                                ProductsActivity::class.java)
                                        intent.putExtra(PRODUCT_TAG_KEY, selectedTag)
                                        startActivity(intent)
                                    }

                                    override fun onLongItemClick(view: View, position: Int) {

                                    }
                                }))
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_tags_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val PRODUCT_TAG_KEY = "com.shopify.shopicruit.product.tag"
    }
}
