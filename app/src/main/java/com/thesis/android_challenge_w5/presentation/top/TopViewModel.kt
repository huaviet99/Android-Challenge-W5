package com.thesis.android_challenge_w5.presentation.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w5.data.RestaurantDataStore
import com.thesis.android_challenge_w5.model.Restaurant

class TopViewModel : ViewModel(){

    private val restaurantList = MutableLiveData<List<Restaurant>>()

    fun fetchRestaurantList(): LiveData<List<Restaurant>>{
       val data = RestaurantDataStore.getAllRestaurantListWithFavoriteChecked("1@gmail.com")
        restaurantList.postValue(data)
        return restaurantList
    }

    fun addFavoriteRestaurant(restaurant: Restaurant){
        RestaurantDataStore.setAddFavoriteRestaurantCallback(object : RestaurantDataStore.AddFavoriteRestaurantCallback {
            override fun onSucceed(restaurantList:List<Restaurant>) {
                Log.d("TopViewModel","add onSucceed")
                this@TopViewModel.restaurantList.postValue(restaurantList)
            }
        })
        RestaurantDataStore.addRestaurantListByEmail("1@gmail.com",restaurant)
    }

    fun removeFavoriteRestaurant(restaurant: Restaurant){
        RestaurantDataStore.setRemoveFavoriteRestaurantCallback(object : RestaurantDataStore.RemoveFavoriteRestaurantCallback {
            override fun onSucceed(restaurantList:List<Restaurant>) {
                Log.d("TopViewModel","remove onSucceed")

              this@TopViewModel.restaurantList.postValue(restaurantList)
            }
        })
        RestaurantDataStore.removeRestaurantListByEmail("1@gmail.com",restaurant)
    }
}