package com.example.unosquarechallenge.network

import com.example.unosquarechallenge.model.PostResponse
import retrofit2.http.GET

interface PostServices {
    @GET("posts")
    suspend fun getPosts(): PostResponse
}