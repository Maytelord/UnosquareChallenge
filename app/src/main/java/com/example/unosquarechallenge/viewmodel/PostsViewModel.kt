package com.example.unosquarechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unosquarechallenge.domain.ResultStatus
import com.example.unosquarechallenge.model.PostResponse
import com.example.unosquarechallenge.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostRepository): ViewModel(){

    private val _postData : MutableLiveData<ResultStatus<PostResponse>> = MutableLiveData()
    val postData: LiveData<ResultStatus<PostResponse>> get() = _postData


    init {
        executeGetPosts()
    }

    var currentJob : Job? = null
    fun executeGetPosts() {
        currentJob = getPosts()
    }


    private fun getPosts() = viewModelScope.launch {
        _postData.value = ResultStatus.Loading()
        _postData.value = repository.getPosts()
    }
}