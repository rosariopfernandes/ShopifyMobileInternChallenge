package com.myshopify.shopicruit.shopifyinternchallenge.tagslist


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myshopify.shopicruit.shopifyinternchallenge.MainActivity
import com.myshopify.shopicruit.shopifyinternchallenge.R
import kotlinx.android.synthetic.main.content_simple_list.*
import mz.co.kidzkare.vaccines.util.RecyclerItemClickListener


class TagsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.content_simple_list, container, false)

        val viewModel = ViewModelProviders.of(this).get(TagsViewModel::class.java)
        viewModel.getTags().observe(this, Observer { tags ->
            tags?.let {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = TagsAdapter(it)
                recyclerView.visibility = View.VISIBLE
                ivEmptyState.visibility = View.GONE

                recyclerView.addOnItemTouchListener(
                        RecyclerItemClickListener(
                                recyclerView,
                                object : RecyclerItemClickListener.OnItemClickListener {
                                    override fun onItemClick(view: View, position: Int) {
                                        val selectedTag = it[position]
                                        val bundle = Bundle()
                                        bundle.putString(MainActivity.PRODUCT_TAG_KEY, selectedTag)
                                        findNavController().navigate(R.id.productsFragment, bundle)
                                    }

                                    override fun onLongItemClick(view: View, position: Int) {

                                    }
                                }))
            }
        })

        return fragmentView
    }

}
