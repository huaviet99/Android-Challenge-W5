package com.thesis.android_challenge_w5.activity.restaurant_list

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.thesis.android_challenge_w5.R
import com.thesis.android_challenge_w5.activity.profile.ProfileViewModel
import com.thesis.android_challenge_w5.databinding.ActivityProfileBinding
import com.thesis.android_challenge_w5.databinding.ActivityRestaurantListBinding
import com.thesis.android_challenge_w5.model.Restaurant
import kotlinx.android.synthetic.main.activity_restaurant_list.*


class RestaurantListActivity : AppCompatActivity(){
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var menu: Menu
    private lateinit var viewModel: RestaurantListViewModel
    private lateinit var binding: ActivityRestaurantListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setupViewModelBinding()
        setupToolbar()
        setupRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        restaurantAdapter.listener = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_restaurant,menu)
        this.menu = menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_list_switch -> {
                val isLinearSwitched: Boolean = restaurantAdapter.toggleItemViewType()

                if(isLinearSwitched){
                    binding.rvRestaurantList.layoutManager = LinearLayoutManager(this)
                    menu[0].icon = ContextCompat.getDrawable(this,R.drawable.ic_grid)
                } else {
                    binding.rvRestaurantList.layoutManager = GridLayoutManager(this,2)
                    menu[0].icon = ContextCompat.getDrawable(this,R.drawable.ic_linear)

                }
            }
        }
        return true
    }

    private fun setupViewModelBinding() {
        viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_list)
        binding.lifecycleOwner = this
        binding.restaurantListViewModel = viewModel

        viewModel.fetchRestaurantList().observe(this, Observer {
            if(it.isNotEmpty()){
                restaurantAdapter.submitList(it)
            } else {
                showToastMessage("Can't find any restaurant")
            }
        })

    }


    private fun setupToolbar(){
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Restaurants"
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

    private fun showToastMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}