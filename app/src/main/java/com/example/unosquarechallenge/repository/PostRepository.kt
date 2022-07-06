package com.example.unosquarechallenge.repository

import com.example.unosquarechallenge.domain.ResultStatus
import com.example.unosquarechallenge.model.PostResponse

interface PostRepository {
    suspend fun getPosts(): ResultStatus<PostResponse>
}