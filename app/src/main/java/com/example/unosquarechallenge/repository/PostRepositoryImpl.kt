package com.example.unosquarechallenge.repository

import com.example.unosquarechallenge.domain.ResultStatus
import com.example.unosquarechallenge.model.PostResponse
import com.example.unosquarechallenge.network.PostServices
import javax.inject.Inject

open class PostRepositoryImpl @Inject constructor(private val api: PostServices) : PostRepository {

    override suspend fun getPosts(): ResultStatus <PostResponse> {
        val response = try {
            ResultStatus.Loading(data = true)
            api.getPosts()

        }catch (exception: Exception){
            return ResultStatus.Error(message = "An error occurred ${exception.message.toString()}")
        }
        ResultStatus.Loading(data = false)
        return ResultStatus.Success(data = response)
    }
}