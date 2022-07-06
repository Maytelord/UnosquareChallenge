package com.example.unosquarechallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unosquarechallenge.databinding.PostFragmentBinding
import com.example.unosquarechallenge.domain.ResultStatus
import com.example.unosquarechallenge.view.adapter.PostListAdapter
import com.example.unosquarechallenge.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment: Fragment() {
    private val viewModel: PostsViewModel by viewModels()
    private val postAdapter = PostListAdapter()
    private lateinit var binding: PostFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        postRVSetup()
        swipeRefreshSetup()
        observers()

        return binding.root
    }

    private fun observers() {
        viewModel.postData.observe(viewLifecycleOwner) { result ->
            when (result) {

                is ResultStatus.Success -> {
                    result.data?.let { response ->
                        postAdapter.submitList(response)

                        binding.swipeRefreshLayout.isRefreshing = false
                        binding.errorTextView.visibility = View.GONE

                    }
                }

                is ResultStatus.Error -> {
                    postAdapter.submitList(emptyList())
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.errorTextView.visibility = View.VISIBLE
                }

                is ResultStatus.Loading -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                    binding.errorTextView.visibility = View.GONE
                }
            }
        }
    }

    private fun postRVSetup() = with(binding.postListRV){
        val comicLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        layoutManager = comicLayoutManager
        adapter = postAdapter
    }

    private fun swipeRefreshSetup() = with(binding.swipeRefreshLayout){
        setOnRefreshListener {
            viewModel.executeGetPosts()


        }
    }


}