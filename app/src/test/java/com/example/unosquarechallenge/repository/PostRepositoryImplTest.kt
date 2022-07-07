package com.example.unosquarechallenge.repository

import com.example.unosquarechallenge.domain.ResultStatus
import com.example.unosquarechallenge.model.PostResponse
import com.example.unosquarechallenge.network.PostServices
import com.example.unosquarechallenge.resources.PostResponseFactory
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryImplTest {
    @Mock
    private lateinit var service: PostServices

    private lateinit var repository: PostRepositoryImpl

    private val postFactory = PostResponseFactory()
    @Before
    fun before() {
        service = Mockito.mock(PostServices::class.java)
        repository = PostRepositoryImpl(service)
    }

    @Test
    fun getPostsSuccessfulTest() = runBlocking {

        val response = postFactory.mockedPostResponse()

        Mockito.`when`(service.getPosts()).thenReturn(response)

        Assert.assertNotNull(response)
        assertEquals(repository.getPosts().data!!, response)
    }

    @Test
    fun getPostsErrorTest() = runBlocking {

        var errorResponse = ResultStatus.Error<PostResponse> ("An error occurred Exception", null)
        Mockito.`when`(service.getPosts()).thenThrow(RuntimeException("Exception"))

        assertEquals(repository.getPosts().message, errorResponse.message)
    }
}