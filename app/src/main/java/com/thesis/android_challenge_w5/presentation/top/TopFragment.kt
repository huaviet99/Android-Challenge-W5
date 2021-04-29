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
import com.thesis.android_challenge_w5.databinding.FragmentTopBinding
import com.thesis.android_challenge_w5.model.Restaurant
import com.thesis.android_challenge_w5.presentation.user.UserFragment

class TopFragment : Fragment() {
    private lateinit var topAdapter: TopAdapter
    private lateinit var topViewModel: TopViewModel
    private lateinit var binding: FragmentTopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topViewModel = ViewModelProvider(this).get(TopViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top, container, false)
        binding.lifecycleOwner = this
        binding.topViewModel = topViewModel
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val userFragment = parentFragment as UserFragment
        val email = userFragment.getEmailFromBundle()
        topViewModel.email.value = email
        topViewModel.fetchRestaurantList().observe(viewLifecycleOwner, Observer {
            activity?.runOnUiThread {
                topAdapter.submitList(it)
            }
        })
    }


    private fun setupRecyclerView() {
        topAdapter = TopAdapter()
        topAdapter.listener = object : TopAdapter.RestaurantAdapterListener {
            override fun onItemClicked(restaurant: Restaurant) {
                Log.d("TopFragment", "onItemClicked= $restaurant")
                if (restaurant.isFavorite) {
                    topViewModel.removeFavoriteRestaurant(restaurant)
                } else {
                    topViewModel.addFavoriteRestaurant(restaurant)
                }
            }
        }
        binding.rvRestaurantList.adapter = topAdapter
    }


    private fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}