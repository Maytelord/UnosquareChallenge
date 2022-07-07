package com.example.unosquarechallenge.resources

import com.example.unosquarechallenge.model.PostItem
import com.example.unosquarechallenge.model.PostResponse
import java.util.concurrent.atomic.AtomicInteger

class PostResponseFactory {
    private val counter = AtomicInteger(0)
    private fun createPost(): PostItem {
        val id = counter.incrementAndGet()
        return PostItem(
            id = id,
            title = "name $id",
            body = "body $id",
            user_id = id
        )
    }

    lateinit var postResponse: PostResponse
    fun mockedPostResponse(): PostResponse {

        postResponse = PostResponse()

        val collection = arrayListOf(
             createPost(),
             createPost(),
             createPost()
        )

        postResponse.addAll(collection)
        return postResponse
    }


}