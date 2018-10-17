package com.myshopify.shopicruit.shopifyinternchallenge.tagslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myshopify.shopicruit.shopifyinternchallenge.api.TagRepository

class TagsViewModel : ViewModel() {

    private val tags: LiveData<ArrayList<String>>

    init {
        tags = TagRepository.getInstance().getTags()
    }

    fun getTags(): LiveData<ArrayList<String>> {
        return tags
    }
}