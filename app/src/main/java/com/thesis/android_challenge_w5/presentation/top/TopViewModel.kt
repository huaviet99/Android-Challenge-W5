package com.thesis.android_challenge_w5.presentation.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w5.data.RestaurantDataStore
import com.thesis.android_challenge_w5.model.Restaurant

class TopViewModel : ViewModel(){

    private val restaurantList = MutableLiveData<List<Restaurant>>()
    val email = MutableLiveData<String>()
    fun fetchRestaurantList(): LiveData<List<Restaurant>>{
       val data = RestaurantDataStore.getAllRestaurantListWithFavoriteChecked(email.value!!)
        restaurantList.postValue(data)
        return restaurantList
    }

    fun addFavoriteRestaurant(restaurant: Restaurant){
        RestaurantDataStore.setAddFavoriteRestaurantCallback(object : RestaurantDataStore.AddFavoriteRestaurantCallback {
            override fun onSucceed(restaurantList:List<Restaurant>) {
                this@TopViewModel.restaurantList.postValue(restaurantList)
            }
        })
        RestaurantDataStore.addFavoriteRestaurantByEmail(email.value!!,restaurant)
    }

    fun removeFavoriteRestaurant(restaurant: Restaurant){
        RestaurantDataStore.setRemoveFavoriteRestaurantCallback(object : RestaurantDataStore.RemoveFavoriteRestaurantCallback {
            override fun onSucceed(restaurantList:List<Restaurant>) {
              this@TopViewModel.restaurantList.postValue(restaurantList)
            }
        })
        RestaurantDataStore.removeFavoriteRestaurantByEmail(email.value!!,restaurant)
    }
}