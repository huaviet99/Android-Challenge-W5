package com.thesis.android_challenge_w5.presentation.restaurant_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.android_challenge_w5.R
import com.thesis.android_challenge_w5.databinding.FragmentRestaurantListBinding
import com.thesis.android_challenge_w5.model.Restaurant
import com.thesis.android_challenge_w5.presentation.signin.SignInViewModel

class RestaurantListFragment : Fragment(){
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var viewModel: RestaurantListViewModel
    private lateinit var binding: FragmentRestaurantListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_list, container, false)
        binding.lifecycleOwner = this
        binding.restaurantListViewModel = viewModel
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.fetchRestaurantList().observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                restaurantAdapter.submitList(it)
            } else {
                showToastMessage("Can't find any restaurant")
            }
        })
    }

    private fun setupRecyclerView(){
        restaurantAdapter = RestaurantAdapter()
        restaurantAdapter.listener = object : RestaurantAdapter.RestaurantAdapterListener {
            override fun onItemClicked(restaurant: Restaurant) {
                showToastMessage(restaurant.name)
            }
        }
        binding.rvRestaurantList.adapter = restaurantAdapter
    }


    private fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}