package com.myshopify.shopicruit.shopifyinternchallenge.tagslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.myshopify.shopicruit.shopifyinternchallenge.R

class TagsAdapter(var tags: ArrayList<String>?) : RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TagsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_tag,
                parent, false)
        return TagsViewHolder(v)
    }

    override fun getItemCount() = tags?.size ?: 0

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        tags?.let {
            val tag = it[position]
            holder.bindTo(tag)
        }
    }

    inner class TagsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvTag: TextView

        init {
            tvTag = v.findViewById(R.id.tvTag)
        }

        fun bindTo(tag: String) {
            tvTag.text = tag
        }
    }
}