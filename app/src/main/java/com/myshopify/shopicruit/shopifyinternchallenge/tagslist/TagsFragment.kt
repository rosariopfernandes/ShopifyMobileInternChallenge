package com.myshopify.shopicruit.shopifyinternchallenge.tagslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myshopify.shopicruit.shopifyinternchallenge.R
import com.myshopify.shopicruit.shopifyinternchallenge.util.RecyclerItemClickListener
import kotlinx.android.synthetic.main.content_simple_list.*


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
                                        val action =
                                                TagsFragmentDirections.actionViewProducts()
                                        action.setProductTag(selectedTag)
                                        findNavController(activity!!, R.id.nav_host_fragment)
                                                .navigate(action)
                                    }

                                    override fun onLongItemClick(view: View, position: Int) {

                                    }
                                }))
            }
        })

        return fragmentView
    }

}
