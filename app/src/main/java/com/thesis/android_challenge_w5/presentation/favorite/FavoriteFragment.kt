package com.thesis.android_challenge_w5.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.android_challenge_w5.R
import com.thesis.android_challenge_w5.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.lifecycleOwner = this
        binding.favoriteViewModel = viewModel
        val view = binding.root
        return view
    }


    fun refresh() {
        viewModel.fetchRestaurantList().observe(viewLifecycleOwner, Observer {
            favoriteAdapter.submitList(it.toMutableList())

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter()
        binding.rvFavoriteList.adapter = favoriteAdapter
    }
}