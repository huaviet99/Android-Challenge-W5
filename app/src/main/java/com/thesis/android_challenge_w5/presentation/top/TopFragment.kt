package com.thesis.android_challenge_w5.presentation.top

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.android_challenge_w5.R
import com.thesis.android_challenge_w5.data.RestaurantDataStore
import com.thesis.android_challenge_w5.databinding.FragmentTopBinding
import com.thesis.android_challenge_w5.model.Restaurant

class TopFragment : Fragment(){
    private lateinit var topAdapter: TopAdapter
    private lateinit var viewModel: TopViewModel
    private lateinit var binding: FragmentTopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top, container, false)
        binding.lifecycleOwner = this
        binding.topViewModel = viewModel
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.fetchRestaurantList().observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                topAdapter.submitList(it)
            }
        })
    }


    fun refresh(){
        viewModel.fetchRestaurantList()
    }
    private fun setupRecyclerView(){
        topAdapter = TopAdapter()
        topAdapter.listener = object : TopAdapter.RestaurantAdapterListener {
            override fun onItemClicked(restaurant: Restaurant) {
                if(restaurant.isFavorite){
                    RestaurantDataStore.removeRestaurantListByEmail("huaviet999@gmail.com",restaurant)
                } else {
                    RestaurantDataStore.addRestaurantListByEmail("huaviet999@gmail.com",restaurant)
                }
            }
        }
        binding.rvRestaurantList.adapter = topAdapter
    }


    private fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}